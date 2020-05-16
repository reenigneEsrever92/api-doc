package eu.reverseengineer.apidoc.openapi.yaml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;

import eu.reverseengineer.apidoc.api.IApiDocChain;
import eu.reverseengineer.apidoc.api.IApiDocProcessor;
import eu.reverseengineer.apidoc.api.IApiDocSubject;
import eu.reverseengineer.apidoc.api.exception.ApiDocProcessorException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

/**
 * Processes a Yaml file to OpenApi-Model from a given uri.
 */
public class YamlProcessor implements IApiDocProcessor<OpenAPI> {

    private URI uri;

    public YamlProcessor(URI uri) {
        this.uri = uri;
    }

    @Override
    public void run(IApiDocSubject<OpenAPI> subject, IApiDocChain<OpenAPI> chain) throws ApiDocProcessorException {
        chain.delegate(subject);

        try {
            if (subject.value().isEmpty()) {
                File file = new File(uri);

                OpenAPI openAPI = new OpenAPIV3Parser()
                    .readContents(Files.readString(file.toPath()))
                    .getOpenAPI();

                subject.setValue(openAPI);
            }
        } catch (IOException e) {
            throw new ApiDocProcessorException(YamlProcessor.class, e);
        }
    }

}