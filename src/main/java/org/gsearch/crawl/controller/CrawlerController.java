package org.gsearch.crawl.controller;

import org.gsearch.crawl.google.api.IGoogleSearchCrawlerService;
import org.gsearch.crawl.google.impl.GoogleSearchCrawlerService;
import org.gsearch.crawl.javascript.api.IJavascriptLibExtractorService;
import org.gsearch.crawl.javascript.api.IWebPageContentLoader;
import org.gsearch.crawl.javascript.impl.MockedJavascriptLibExtractorService;
import org.gsearch.crawl.javascript.impl.WebPageContentLoader;
import org.gsearch.http.api.IHttpClient;
import org.gsearch.http.impl.HttpClient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CrawlerController implements ICrawlerController {

    private final IGoogleSearchCrawlerService googleSearchCrawlerService;
    private final IWebPageContentLoader webPageContentLoader;
    private final IJavascriptLibExtractorService javascriptLibExtractorService;
    private final IHttpClient httpClient;

    public CrawlerController() {
        httpClient = new HttpClient();
        googleSearchCrawlerService = new GoogleSearchCrawlerService(httpClient);
        webPageContentLoader = new WebPageContentLoader(httpClient);
        javascriptLibExtractorService = new MockedJavascriptLibExtractorService();
    }

    @Override
    public List<String> findTopUsedJSLibs(String query) {

        ForkJoinPool customThreadPool = new ForkJoinPool(5);
        Map<String, Long> jsCount = null;
        try {
            jsCount = customThreadPool.submit(
                    () -> googleSearchCrawlerService.extractResults(query)
                            .parallelStream()
                            .map(webPageContentLoader::loadContent)
                            .map(javascriptLibExtractorService::getLibraries)
                            .flatMap(libs -> libs.stream())
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return jsCount
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(es -> es.getKey())
                .collect(Collectors.toList());


    }


}
