package org.gsearch.crawl.javascript.impl;

import org.gsearch.crawl.javascript.api.IWebPageContentLoader;
import org.gsearch.http.api.IHttpClient;
import org.gsearch.http.impl.HttpGetCommand;

public class WebPageContentLoader implements IWebPageContentLoader {

    private final IHttpClient httpClient;

    public WebPageContentLoader(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String loadContent(String url) {
        HttpGetCommand<WebPageContentResponse> getCommand = new HttpGetCommand<>(
                s -> new WebPageContentResponse(s),
                url
        );
        return httpClient.execute(getCommand).map(WebPageContentResponse::getResponse).orElse("");
    }
}
