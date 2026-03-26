package exceptions;

public class InvalidCommandException extends AppException{
    public InvalidCommandException(String command){
        super("Invalid " + command + " command", 400, "INVALID_COMMAND");
    }
}
