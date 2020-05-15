package eu.reverseengineer.apidoc.api;

public interface IApiDocGenerator<T, R> {
    void run(IApiDocSubject<T, R> subject, IApiDocChain<T, R> chain);
}
