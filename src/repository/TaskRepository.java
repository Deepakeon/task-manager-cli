package repository;

import exceptions.TaskNotFoundException;
import helpers.FileManager;
import helpers.JsonMapper;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskRepository {
    final private FileManager fileManager;
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskRepository(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public ArrayList<Task> getTasks(){
        if(tasks.isEmpty()){
            String json = fileManager.readFile();
            tasks = JsonMapper.stringToTask(json);
        }
        return tasks;
    }

    public ArrayList<Task> getTasks(TaskStatus status){
        if(tasks.isEmpty()){
            String json = fileManager.readFile();
            tasks = JsonMapper.stringToTask(json);
        }
        return new ArrayList<>(tasks.stream().filter(task -> task.getStatus() == status).toList());
    }

    public void saveJson(ArrayList<Task> tasks){
        String updatedJson = JsonMapper.taskToString(tasks);
        fileManager.atomicWriteFile(updatedJson);
    }

    public void addTask(Task task){
        ArrayList<Task> tasks = getTasks();
        tasks.add(task);
        saveJson(tasks);
    }

    public int getTaskIndexById(int id, ArrayList<Task> tasks){
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i).getId() == id){
                return i;
            }
        }

        return -1;
    }

    public Task getTaskById(int id, ArrayList<Task> tasks){
        int idx = getTaskIndexById(id, tasks);
        if(idx == -1){
            throw new TaskNotFoundException();
        }

        return tasks.get(idx);
    }

    public void updateTask(int id, Consumer<Task> updater){
        ArrayList<Task> tasks = getTasks();
        Task task = getTaskById(id, tasks);
        updater.accept(task);
        task.setUpdatedAt(System.currentTimeMillis());
        saveJson(tasks);
    }

    public void deleteTask(int id){
        ArrayList<Task> tasks = getTasks();
        int taskIndex = getTaskIndexById(id, tasks);

        if(taskIndex == -1){
            throw new TaskNotFoundException();
        }

        tasks.remove(taskIndex);
        saveJson(tasks);
    }
}
