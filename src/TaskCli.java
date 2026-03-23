import helpers.FileManager;
import repository.TaskRepository;
import services.CommandManagerService;
import services.TaskService;

public class TaskCli {
    public static void main(String[] args) {
        CommandManagerService command = new CommandManagerService(new TaskService(new TaskRepository(new FileManager("tasks.json"))));
        command.init(args);
    }
}