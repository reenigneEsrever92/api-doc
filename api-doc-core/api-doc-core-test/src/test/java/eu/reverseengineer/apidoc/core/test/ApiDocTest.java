package eu.reverseengineer.apidoc.core.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.internal.bytebuddy.asm.MemberSubstitution.Substitution.Chain;
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
        IApiDocContext<String, String> ctx = new ApiDocContext<>();

        ctx.add((subject, delegate) -> {
            subject.setValue("testValue");
        });

        assertThat(ctx.generate(
            (subject) -> subject.value().get()
        ).get()).isEqualTo("testValue");
    }

    @Test
    @DisplayName("ApiDoc :: Core :: Multiple Simple Generators")
    public void testMultipleSimpleGenerators() throws ApiDocNoResultException {
        IApiDocContext<String, String> ctx = new ApiDocContext<>();

        ctx.add((subject, chain) -> {
            subject.setValue("testApi");
            chain.delegate(subject);
        });

        ctx.add((subject, chain) -> {
            assertThat(subject.value().get())
                .isEqualTo("testApi");
                
            subject.setValue("result2");
        });

        assertThat(ctx.generate(
            (subject) -> subject.value().get()
        ).get()).isEqualTo("result2");
    }

    @Test
    @DisplayName("ApiDoc :: Core :: Overcomitted Delegates")
    public void testOvercomittedDelegates() throws ApiDocNoResultException {
        IApiDocContext<String, String> ctx = new ApiDocContext<>();

        ctx.add((subject, chain) -> {
            subject.setValue("testApi");
            chain.delegate(subject);
        });

        ctx.add((subject, chain) -> {
            chain.delegate(subject);
            assertThat(subject.value().get())
                .isEqualTo("testApi");
                
            subject.setValue("result2");
        });

        assertThat(ctx.generate(
            (subject) -> subject.value().get()
        ).get()).isEqualTo("result2");
    }
}