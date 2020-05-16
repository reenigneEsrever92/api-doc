package eu.reverseengineer.apidoc.api;

public interface IApiDocChain<T> {
    void delegate(IApiDocSubject<T> subject);
}