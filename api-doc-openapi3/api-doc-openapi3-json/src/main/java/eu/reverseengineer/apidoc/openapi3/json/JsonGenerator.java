package eu.reverseengineer.apidoc.openapi3.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.reverseengineer.apidoc.api.IApiDocChain;
import eu.reverseengineer.apidoc.api.IApiDocGenerator;
import eu.reverseengineer.apidoc.api.IApiDocProcessor;
import eu.reverseengineer.apidoc.api.IApiDocSubject;
import eu.reverseengineer.apidoc.api.exception.ApiDocGeneratorException;
import eu.reverseengineer.apidoc.api.exception.ApiDocProcessorException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.ObjectMapperFactory;

public class JsonGenerator implements IApiDocGenerator<OpenAPI, String> {

    private ObjectMapper mapper = ObjectMapperFactory.createJson();

    @Override
    public String generate(IApiDocSubject<OpenAPI> subject) throws ApiDocGeneratorException {
        try {
            return mapper.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            throw new ApiDocGeneratorException(JsonGenerator.class, "Could not generate json!", e);
        }
    }
}
