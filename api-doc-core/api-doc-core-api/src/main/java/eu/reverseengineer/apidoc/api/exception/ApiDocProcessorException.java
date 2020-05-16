package eu.reverseengineer.apidoc.api.exception;

import eu.reverseengineer.apidoc.api.IApiDocProcessor;

public class ApiDocProcessorException extends ApiDocException {

    public ApiDocProcessorException(Class<? extends IApiDocProcessor<?>> c, String message) {
        this(c, message, null);
    }

    public ApiDocProcessorException(Class<? extends IApiDocProcessor<?>> c, Throwable cause) {
        this(c, null, cause);    
    }
    
    public ApiDocProcessorException(Class<? extends IApiDocProcessor<?>> c, String message, Throwable cause) {
        super("Processor: " + c.getName() + ", did not finish succesfully.\n" + message, cause);
    }
}