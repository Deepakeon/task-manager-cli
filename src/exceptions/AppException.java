package exceptions;

public class AppException extends RuntimeException{
    final private int statusCode;
    final private String errorCode;

    public AppException(String message, int statusCode, String errorCode){
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
    public AppException(String message, int statusCode, String errorCode, Throwable err){
        super(message, err);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
