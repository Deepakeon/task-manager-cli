package helpers;

import exceptions.FileOperationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileManager {
    final private Path filePath;
    final private Path tempPath;

    public FileManager(String filePath){
        this.filePath = Path.of(filePath);
        this.tempPath = Path.of(filePath + ".tmp");
    }

    public boolean doesFileNotExist(Path filePath){
        return !Files.exists(filePath);
    }

    public void createFilesIfNotExist(){
        try{
            if(doesFileNotExist(filePath)){
                Files.createFile(filePath);
            }

            if(doesFileNotExist(tempPath)){
                Files.createFile(tempPath);
            }
        } catch(IOException e){
            throw new FileOperationException("Failed to create file: " + e.getMessage(), e);
        }
    }

    public String readFile(){
        createFilesIfNotExist();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))){
            StringBuilder fileContents = new StringBuilder();
            String content;
            while((content = reader.readLine())!= null){
                fileContents.append(content);
            }
            return fileContents.toString();
        } catch (IOException e) {
            throw new FileOperationException("Failed to read file: " + filePath, e);
        }
    }

    public void completeAtomicWrite(){
        try{
            Files.move(tempPath, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileOperationException("Failed to write atomically" + e.getMessage(), e);
        }
    }

    public void atomicWriteFile(String fileContents){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath.toFile()))){
            writer.write(fileContents);
            writer.flush();
        }catch (IOException e) {
            throw new FileOperationException("Failed to write to file: " + filePath.toString(), e);
        }
        completeAtomicWrite();
    }
}
