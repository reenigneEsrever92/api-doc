package eu.reverseengineer.apidoc.core.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import eu.reverseengineer.apidoc.api.IApiDocContext;
import eu.reverseengineer.apidoc.api.exception.ApiDocNoResultException;
import eu.reverseengineer.apidoc.core.impl.ApiDocContext;

@TestInstance(Lifecycle.PER_CLASS)
public class ApiDocTest {

    @Test
    @DisplayName("ApiDoc :: Core :: Create Context")
    public void testCreateContext() {
        IApiDocContext<?, ?> ctx = new ApiDocContext<>();

        assertThat(ctx).isNotNull();
    }

    @Test
    @DisplayName("ApiDoc :: Core :: Simple Generator")
    public void testSimpleGenerator() throws ApiDocNoResultException {
        IApiDocContext<Object, String> ctx = new ApiDocContext<>();

        ctx.add((subject, delegate) -> {
            subject.setApi(new Object());
            subject.setResult("result");
        });

        assertThat(ctx.run()).isEqualTo("result");
    }

    @Test
    @DisplayName("ApiDoc :: Core :: Multiple Simple Generators")
    public void testMultipleSimpleGenerators() throws ApiDocNoResultException {
        IApiDocContext<String, String> ctx = new ApiDocContext<>();

        ctx.add((subject, chain) -> {
            subject.setApi("testApi");
            subject.setResult("result");
            chain.delegate(subject);
        });

        ctx.add((subject, chain) -> {
            assertThat(subject.api().get())
                .isEqualTo("testApi");
                
            subject.setResult("result2");
        });

        assertThat(ctx.run()).isEqualTo("result2");
    }
}