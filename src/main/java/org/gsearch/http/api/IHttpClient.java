package org.gsearch.http.api;

import java.util.Optional;

public interface IHttpClient {

    <T extends IHttpResponse> Optional<T> execute(IHttpCommand<T> command);

}
