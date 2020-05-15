package eu.reverseengineer.apidoc.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.reverseengineer.apidoc.api.IApiDocContext;
import eu.reverseengineer.apidoc.api.IApiDocChain;
import eu.reverseengineer.apidoc.api.IApiDocGenerator;
import eu.reverseengineer.apidoc.api.IApiDocSubject;
import eu.reverseengineer.apidoc.api.exception.ApiDocNoResultException;

public class ApiDocContext<T, R> implements IApiDocContext<T, R>, IApiDocChain<T, R> {

    private List<IApiDocGenerator<T, R>> generators = new ArrayList<>(16);
    private int executionCounter = 0;

    @Override
    public IApiDocContext<T, R> add(IApiDocGenerator<T, R> generator) {
        generators.add(generator);
        return this;
    }

    @Override
    public R run() throws ApiDocNoResultException{
        IApiDocSubject<T, R> subject = new ApiDocSubject<>();
        executionCounter = 0;
        delegate(subject);
        return subject.result().orElseThrow(() -> new ApiDocNoResultException(this));
    }

    @Override
    public void delegate(IApiDocSubject<T, R> subject) {
        generators.get(executionCounter++).run(subject, this);
    }

    private static class ApiDocSubject<T, R> implements IApiDocSubject<T, R> {

        private T api;
        private R result;

        @Override
        public void setApi(T api) {
            this.api = api;
        }

        @Override
        public Optional<T> api() {
            return Optional.ofNullable(api);
        }

        @Override
        public void setResult(R result) {
            this.result = result;
        }

        @Override
        public Optional<R> result() {
            return Optional.ofNullable(result);
        }

    }
}