package org.gsearch.crawl.google.impl;

import org.gsearch.http.api.IHttpResponse;

import java.util.Set;

public class GoogleSearchResponse implements IHttpResponse<Set<String>> {

    private final Set<String> data;

    public GoogleSearchResponse(Set<String> data) {
        this.data = data;
    }

    @Override
    public Set<String> getResponse() {
        return data;
    }
}
