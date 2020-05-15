package eu.reverseengineer.apidoc.api;

public interface IApiDocChain<T, R> {
    void delegate(IApiDocSubject<T, R> subject);
}