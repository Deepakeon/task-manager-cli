package exceptions;

public class FileOperationException extends AppException{
    public FileOperationException(String message, Throwable err){
        super(message, 500, "FILE_OPERATION_EXCEPTION", err);
    }
}
