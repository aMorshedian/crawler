package org.gsearch.http.api;

import java.util.Optional;
import java.util.function.Function;

public interface IHttpCommand<T extends IHttpResponse> {

    Function<String, T> getResponseProcessor();
    String getURL();

    Optional<T> execute();
}
