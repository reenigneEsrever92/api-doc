package eu.reverseengineer.apidoc.api.exception;

public class ApiDocException extends Exception {

    private static final long serialVersionUID = 1L;

    public ApiDocException() {
        super();
    }

    public ApiDocException(String message) {
        super(message);
    }

    public ApiDocException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiDocException(Throwable cause) {
        super(cause);
    }

    protected ApiDocException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}