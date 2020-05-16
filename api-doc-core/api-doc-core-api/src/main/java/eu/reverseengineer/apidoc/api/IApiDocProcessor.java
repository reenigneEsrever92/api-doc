package eu.reverseengineer.apidoc.api;

import eu.reverseengineer.apidoc.api.exception.ApiDocProcessorException;

public interface IApiDocProcessor<T> {
    void run(IApiDocSubject<T> subject, IApiDocChain<T> chain) throws ApiDocProcessorException;
}
