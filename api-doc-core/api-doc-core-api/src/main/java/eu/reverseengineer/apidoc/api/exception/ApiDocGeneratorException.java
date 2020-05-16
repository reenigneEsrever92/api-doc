package eu.reverseengineer.apidoc.api.exception;

import eu.reverseengineer.apidoc.api.IApiDocGenerator;

public class ApiDocGeneratorException extends ApiDocException {
    
    public ApiDocGeneratorException(Class<? extends IApiDocGenerator> clazz, String message) {
        this(clazz, message, null);
    }

    public ApiDocGeneratorException(Class<? extends IApiDocGenerator> clazz, String message, Throwable cause) {
        super("Error generating final result in Generator: " + clazz.getName() + ".\n" + message, cause);
    }
}