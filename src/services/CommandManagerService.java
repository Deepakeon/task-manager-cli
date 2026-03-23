package services;

import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManagerService {
    private final TaskService taskService;

    public CommandManagerService(TaskService taskService){
        this.taskService = taskService;
    }

    public void init(String[] args){
        String operation = args[0].toLowerCase();

        switch (operation){
            case "add":
                if (args.length != 2){
                    System.out.println("Invalid add command");
                    return;
                }
                taskService.addTask(args[1]);
                break;
            case "update":
                if (args.length != 3){
                    System.out.println("Invalid update command");
                    return;
                }
                taskService.updateTask(Integer.parseInt(args[1]), args[2]);
                break;
            case "delete":
                if(args.length != 2){
                    System.out.println("Invalid delete command");
                    return;
                }
                taskService.deleteTask(Integer.parseInt(args[1]));
                break;
            case "mark-in-progress":
                if(args.length != 2){
                    System.out.println("Invalid command");
                    return;
                }
                taskService.updateTask(Integer.parseInt(args[1]), TaskStatus.IN_PROGRESS);
                break;
            case "mark-done":
                if(args.length != 2){
                    System.out.println("Invalid command");
                    return;
                }
                taskService.updateTask(Integer.parseInt(args[1]), TaskStatus.DONE);
                break;
            case "list":
                ArrayList<Task> tasks = new ArrayList<>();

                if(args.length == 2){
                    TaskStatus status = TaskStatus.NOT_STARTED;
                    if(args[1].equals("done")){
                        status = TaskStatus.DONE;
                    }else if (args[1].equals("in-progress")){
                        status = TaskStatus.IN_PROGRESS;
                    }

                    tasks = taskService.getTasks(status);
                }else{
                    tasks = taskService.getTasks();
                }
                for(Task task: tasks){
                    System.out.println(task.serializeToJsonString());
                }
                break;
            default:
                System.out.println("Command not found");

        }
    }
}
