package eu.reverseengineer.apidoc.api;

import eu.reverseengineer.apidoc.api.exception.ApiDocGeneratorException;

public interface IApiDocGenerator<T, R> {
    R generate(IApiDocSubject<T> subject) throws ApiDocGeneratorException;
}