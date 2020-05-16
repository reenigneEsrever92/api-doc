package eu.reverseengineer.apidoc.openapi.yaml;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import eu.reverseengineer.apidoc.api.IApiDocContext;
import eu.reverseengineer.apidoc.core.impl.ApiDocContext;
import io.swagger.v3.oas.models.OpenAPI;

@TestInstance(Lifecycle.PER_CLASS)
public class YamlProcessorTest {

    @Test
    @DisplayName("ApiDoc :: OpenApi 3 :: Yaml :: Process Yaml")
    public void testProcessYaml() throws URISyntaxException {
        IApiDocContext<OpenAPI, OpenAPI> ctx = new ApiDocContext<>();

        ctx.add(new YamlProcessor(this.getClass().getClassLoader().getResource("petstore.yaml").toURI()));

        OpenAPI api = ctx.generate((subject) -> subject.value().get()).get();

        assertThat(api.getInfo().getTitle())
            .isEqualTo("Swagger Petstore");

        assertThat(api.getInfo().getVersion())
            .isEqualTo("1.0.0");
    }

}