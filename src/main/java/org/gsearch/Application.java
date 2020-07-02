package org.gsearch;

import org.gsearch.crawl.controller.CrawlerController;

public class Application {

    public static void main(String[] args) {
        new CrawlerController().findTopUsedJSLibs(args[0]).stream().forEach(System.out::println);
    }
}
