package exceptions;

public class ValidationException extends AppException{
    public ValidationException(String message){
        super(message, 400, "VALIDATION_FAILED");
    }
}
