package eu.reverseengineer.apidoc.api;

public interface IApiDocGenerator<T, R> {
    R generate(IApiDocSubject<T> subject);
}