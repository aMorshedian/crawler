package org.gsearch.crawl.google.api;

import java.util.Set;

public interface IGoogleSearchCrawlerService {

    Set<String> extractResults(String query);
}
