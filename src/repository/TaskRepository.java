package repository;

import helpers.FileManager;
import helpers.JsonMapper;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskRepository {
    final public FileManager fileManager;

    public TaskRepository(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public ArrayList<Task> loadJson(){
        String json = fileManager.readFile();
        return JsonMapper.stringToTask(json);
    }

    public void saveJson(ArrayList<Task> tasks){
        String updatedJson = JsonMapper.taskToString(tasks);
        fileManager.writeFile(updatedJson);
    }

    public void addTask(Task task){
        ArrayList<Task> tasks = loadJson();
        tasks.add(task);
        saveJson(tasks);
    }

    public void updateTask(int id, Consumer<Task> updater){
        ArrayList<Task> tasks = loadJson();
        for(Task task: tasks){
            if(task.getId() == id){
                updater.accept(task);
                task.setUpdatedAt(System.currentTimeMillis());
                break;
            }
        }
        saveJson(tasks);
    }

    public void deleteTask(int id){
        ArrayList<Task> tasks = loadJson();
        tasks.removeIf(task -> task.getId() == id);
        saveJson(tasks);
    }

    public ArrayList<Task> getTasks(){
        return loadJson();
    }

    public ArrayList<Task> getTasks(TaskStatus status){
        ArrayList<Task> tasks = loadJson();
        return new ArrayList<>(tasks.stream().filter(task -> task.getStatus() == status).toList());
    }
}
