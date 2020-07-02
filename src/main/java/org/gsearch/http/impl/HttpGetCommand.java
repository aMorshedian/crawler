package org.gsearch.http.impl;

import org.gsearch.http.api.IHttpCommand;
import org.gsearch.http.api.IHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HttpGetCommand<T extends IHttpResponse> implements IHttpCommand<T> {

    public static final String USER_AGENT_KEY = "User-Agent";
    public static final String GOOGLE_CHROME_USER_AGENT_VALUE = "Google Chrome/36";
    private final Function<String, T> responseProcessor;
    private final String url;

    public HttpGetCommand(Function<String, T> responseProcessor, String url) {
        this.responseProcessor = responseProcessor;
        this.url = url;
    }

    @Override
    public Function<String, T> getResponseProcessor() {
        return responseProcessor;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public Optional<T> execute() {
        try {
            URL get = new URL(url);
            URLConnection connection = get.openConnection();
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            connection.addRequestProperty(USER_AGENT_KEY, GOOGLE_CHROME_USER_AGENT_VALUE);//put the browser name/version
            String response
                    = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            return Optional.of(responseProcessor.apply(response));
        } catch (IOException e) {
        }

        return Optional.empty();
    }
}
