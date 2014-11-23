package Exceptions;

public class ExceptionView {
    private String exception;
    private String message;

    public ExceptionView(Throwable throwable) {
        exception = throwable.getClass().getName();
        message = throwable.getMessage();
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
