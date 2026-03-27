package services;

import model.Task;
import model.TaskStatus;
import repository.TaskRepository;
import validation.TaskValidator;

import java.util.ArrayList;

public class TaskService {
    private final TaskRepository taskRepository;
    private int latestId = 0;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    private int getLatestId(){
            ArrayList<Task> tasks = taskRepository.getTasks();
            if(!tasks.isEmpty()){
                latestId = tasks.get(tasks.size() - 1).getId();
            }
            return latestId;
    }

    public ArrayList<Task> getTasks(){
        return taskRepository.getTasks();
    }
    public ArrayList<Task> getTasks(TaskStatus status){
        return taskRepository.getTasks(status);
    }

    public Task getTaskById(int id){
        ArrayList<Task> tasks = getTasks();
        return taskRepository.getTaskById(id, tasks);
    }

    public void addTask(String description){
            long now = System.currentTimeMillis();
            int newTaskId = getLatestId() + 1;
            Task task = new Task(now, description, newTaskId, TaskStatus.NOT_STARTED, now);
            TaskValidator.validate(task);
            taskRepository.addTask(task);
    }

    public void updateTask(int id, TaskStatus status){
            taskRepository.updateTask(id, task -> task.setStatus(status));
    }
    public void updateTask(int id, String description){
            TaskValidator.validateDescription(description);
            taskRepository.updateTask(id, task -> task.setDescription(description));
    }

    public void deleteTask(int id){
            taskRepository.deleteTask(id);
    }

}
