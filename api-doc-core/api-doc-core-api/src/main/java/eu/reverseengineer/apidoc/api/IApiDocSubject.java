package eu.reverseengineer.apidoc.api;

import java.util.Optional;

/**
 * Represents the subject all filters are gonna attend to for forming api documentation.
 * @param <T> The type of the contrete subject
 */
public interface IApiDocSubject<T> {
    void setValue(T value);
    Optional<T> value();
}
