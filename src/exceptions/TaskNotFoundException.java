package exceptions;

public class TaskNotFoundException extends AppException{
    public TaskNotFoundException(){
        super("Task not found", 404, "TASK_NOT_FOUND");
    }
}
