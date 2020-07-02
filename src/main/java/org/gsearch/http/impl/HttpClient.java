package org.gsearch.http.impl;

import org.gsearch.http.api.IHttpClient;
import org.gsearch.http.api.IHttpCommand;
import org.gsearch.http.api.IHttpResponse;

import java.util.Optional;

public class HttpClient implements IHttpClient {

    @Override
    public <T extends IHttpResponse> Optional<T> execute(IHttpCommand<T> command) {
        return command.execute();
    }
}
