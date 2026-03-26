package services;

import exceptions.FileOperationException;
import exceptions.InvalidCommandException;
import exceptions.TaskNotFoundException;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;

public class CommandManagerService {
    private final TaskService taskService;

    public CommandManagerService(TaskService taskService){
        this.taskService = taskService;
    }

    public void init(String[] args){
        String operation = args[0].toLowerCase();

        try {

            switch (operation) {
                case "add":
                    if (args.length != 2) {
                        throw new InvalidCommandException("Add");
                    }
                    taskService.addTask(args[1]);
                    break;
                case "update":
                    if (args.length != 3) {
                        throw new InvalidCommandException("Update");
                    }
                    taskService.updateTask(Integer.parseInt(args[1]), args[2]);
                    break;
                case "delete":
                    if (args.length != 2) {
                        throw new InvalidCommandException("Delete");
                    }
                    taskService.deleteTask(Integer.parseInt(args[1]));
                    break;
                case "mark-in-progress":
                    if (args.length != 2) {
                        throw new InvalidCommandException("Mark in progress");
                    }
                    taskService.updateTask(Integer.parseInt(args[1]), TaskStatus.IN_PROGRESS);
                    break;
                case "mark-done":
                    if (args.length != 2) {
                        throw new InvalidCommandException("Mark done");
                    }
                    taskService.updateTask(Integer.parseInt(args[1]), TaskStatus.DONE);
                    break;
                case "list":
                    ArrayList<Task> tasks = new ArrayList<>();

                    if (args.length == 2) {
                        TaskStatus status = TaskStatus.NOT_STARTED;
                        if (args[1].equals("done")) {
                            status = TaskStatus.DONE;
                        } else if (args[1].equals("in-progress")) {
                            status = TaskStatus.IN_PROGRESS;
                        }

                        tasks = taskService.getTasks(status);
                    } else {
                        tasks = taskService.getTasks();
                    }
                    for (Task task : tasks) {
                        System.out.println(task.serializeToJsonString());
                    }
                    break;
                default:
                    System.out.println("Command not found");

            }
        }catch (FileOperationException | TaskNotFoundException | InvalidCommandException e){
            System.out.println(e.getMessage());
        }
    }
}
