package services;

import exceptions.InvalidCommandException;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandManagerService {
    private final TaskService taskService;

    public CommandManagerService(TaskService taskService){
        this.taskService = taskService;
    }

    private HashMap<String, String> getCommandAttributesMap(String[] args){
        HashMap<String, String> commandsMap = new HashMap<>();
        for(int i=0;i< args.length -1;i+=2){
            commandsMap.put(args[i],args[i+1]);
        }

        return commandsMap;
    }

    public void init(String[] args){
        String operation = args[0].toLowerCase();
        HashMap<String, String> commandAttributesMap = getCommandAttributesMap(Arrays.copyOfRange(args, 1, args.length));

        try {

            switch (operation) {
                case "add" -> {
                    String description = commandAttributesMap.get("--description");

                    if(description == null){
                        throw new InvalidCommandException(operation);
                    }

                    taskService.addTask(description);
                }
                case "update" -> {
                    int id = Integer.parseInt(commandAttributesMap.get("--id"));
                    String description = commandAttributesMap.get("--description");

                    if (description == null){
                        throw new InvalidCommandException(operation);
                    }

                    taskService.updateTask(id, description.trim());
                }
                case "delete" -> {
                    int id = Integer.parseInt(commandAttributesMap.get("--id"));
                    taskService.deleteTask(id);
                }
                case "mark-in-progress" -> {
                    int id = Integer.parseInt(commandAttributesMap.get("--id"));
                    taskService.updateTask(id, TaskStatus.IN_PROGRESS);

                }
                case "mark-done" -> {
                    int id = Integer.parseInt(commandAttributesMap.get("--id"));
                    taskService.updateTask(id, TaskStatus.DONE);

                }
                case "list" -> {
                    ArrayList<Task> tasks;
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
                }
                default -> {
                    System.out.println("Command not found");
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
