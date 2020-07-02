package org.gsearch.crawl.javascript.api;

import java.util.Set;

public interface IJavascriptLibExtractorService {

    Set<String> getLibraries(String content);

}
