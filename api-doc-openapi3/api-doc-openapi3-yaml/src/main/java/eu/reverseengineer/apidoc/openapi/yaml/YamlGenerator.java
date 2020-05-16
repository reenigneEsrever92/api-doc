package eu.reverseengineer.apidoc.openapi.yaml;

import eu.reverseengineer.apidoc.api.IApiDocGenerator;
import eu.reverseengineer.apidoc.api.IApiDocSubject;
import eu.reverseengineer.apidoc.api.exception.ApiDocGeneratorException;
import io.swagger.v3.oas.models.OpenAPI;

public class YamlGenerator implements IApiDocGenerator<OpenAPI, String> {

    @Override
    public String generate(IApiDocSubject<OpenAPI> subject) throws ApiDocGeneratorException {
        if(subject.value().isEmpty()) {
            throw new ApiDocGeneratorException(YamlGenerator.class, "No model present to generate yaml from.");
        }

        //TODO implement yaml conversion
        return null;
    }
    
}