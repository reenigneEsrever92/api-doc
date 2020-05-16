package eu.reverseengineer.apidoc.core.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.reverseengineer.apidoc.api.IApiDocContext;
import eu.reverseengineer.apidoc.api.IApiDocGenerator;
import eu.reverseengineer.apidoc.api.IApiDocChain;
import eu.reverseengineer.apidoc.api.IApiDocProcessor;
import eu.reverseengineer.apidoc.api.IApiDocResult;
import eu.reverseengineer.apidoc.api.IApiDocSubject;
import eu.reverseengineer.apidoc.api.exception.ApiDocException;
import eu.reverseengineer.apidoc.api.exception.ApiDocNoResultException;

public class ApiDocContext<T, R> implements IApiDocContext<T, R>, IApiDocChain<T> {

    private List<IApiDocProcessor<T>> processors = new ArrayList<>(16);
    private int executionCounter = 0;

    @Override
    public IApiDocContext<T, R> add(IApiDocProcessor<T> generator) {
        processors.add(generator);
        return this;
    }

    @Override
    public IApiDocResult<R> generate(IApiDocGenerator<T, R> generator) {
        IApiDocSubject<T> subject = new ApiDocSubject<>();
        executionCounter = 0;
        delegate(subject);
        return new ApiDocResult<T, R>(generator, subject);
    }

    @Override
    public void delegate(IApiDocSubject<T> subject) {
        if (executionCounter >= processors.size())
            return;

        try {
            processors.get(executionCounter++).run(subject, this);
        } catch (ApiDocException e) {
            throw new RuntimeException(e);
        }
    }

    private static class ApiDocResult<T, R> implements IApiDocResult<R> {

        private IApiDocGenerator<T, R> generator;
        private IApiDocSubject<T> subject;

        public ApiDocResult(IApiDocGenerator<T, R> generator, IApiDocSubject<T> subject) {
            this.generator = generator;
            this.subject = subject;
        }

        @Override
        public R get() {
            return generator.generate(subject);
        }

    }

    private static class ApiDocSubject<T> implements IApiDocSubject<T> {

        private T value;

        @Override
        public void setValue(T api) {
            this.value = api;
        }

        @Override
        public Optional<T> value() {
            return Optional.ofNullable(value);
        }
    }
}