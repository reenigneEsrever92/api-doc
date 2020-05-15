package eu.reverseengineer.apidoc.api.exception;

import eu.reverseengineer.apidoc.api.IApiDocContext;

public class ApiDocNoResultException extends ApiDocException {

    private static final long serialVersionUID = 1L;

    public ApiDocNoResultException(IApiDocContext<?, ?> context) {
        super("ApiDoc generators did not produce any result.\n" + context);
    }   
}