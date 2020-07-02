package org.gsearch.crawl.controller;

import java.util.List;

public interface ICrawlerController {

    List<String> findTopUsedJSLibs(String query);
}
