package eu.reverseengineer.apidoc.api;

public interface IApiDocContext<T, R> {
    IApiDocContext<T, R> add(IApiDocProcessor<T> processor);
    IApiDocResult<R> generate(IApiDocGenerator<T, R> generator);
}
