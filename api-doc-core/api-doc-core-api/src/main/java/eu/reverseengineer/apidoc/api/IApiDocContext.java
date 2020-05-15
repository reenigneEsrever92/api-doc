package eu.reverseengineer.apidoc.api;

import eu.reverseengineer.apidoc.api.exception.ApiDocNoResultException;

public interface IApiDocContext<T, R> {
    IApiDocContext<T, R> add(IApiDocGenerator<T, R> generator);
    R run() throws ApiDocNoResultException;
}
