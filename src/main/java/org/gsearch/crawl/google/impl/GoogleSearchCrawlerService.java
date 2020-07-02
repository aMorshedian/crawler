package org.gsearch.crawl.google.impl;

import org.gsearch.crawl.google.api.IGoogleSearchCrawlerService;
import org.gsearch.http.api.IHttpClient;
import org.gsearch.http.impl.HttpGetCommand;

import java.util.Collections;
import java.util.Set;

public class GoogleSearchCrawlerService implements IGoogleSearchCrawlerService {

    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=%s";
    private final IHttpClient httpClient;

    public GoogleSearchCrawlerService(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Set<String> extractResults(String query) {
        HttpGetCommand<GoogleSearchResponse> getCommand = new HttpGetCommand<>(new GoogleSearchResponseProcessor(),
                String.format(GOOGLE_SEARCH_URL, query));

        return httpClient.execute(getCommand)
                .map(r -> r.getResponse())
                .orElse(Collections.emptySet());
    }
}
