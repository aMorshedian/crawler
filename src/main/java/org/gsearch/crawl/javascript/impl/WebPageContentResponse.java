package org.gsearch.crawl.javascript.impl;

import org.gsearch.http.api.IHttpResponse;

public class WebPageContentResponse implements IHttpResponse<String> {
    private final String data;

    public WebPageContentResponse(String data) {
        this.data = data;
    }

    @Override
    public String getResponse() {
        return data;
    }
}
