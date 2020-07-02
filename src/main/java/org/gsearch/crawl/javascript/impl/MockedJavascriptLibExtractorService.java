package org.gsearch.crawl.javascript.impl;

import org.gsearch.crawl.javascript.api.IJavascriptLibExtractorService;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MockedJavascriptLibExtractorService implements IJavascriptLibExtractorService {
    private final static String[] JS_LIBS = {"anime.js", "Bideo.js", "Chart.js", "Choreographer-js", "Cleave.js",
            "D3.js", "Granim.js", "Multiple.js", "Popper.js", "TweenJS"};

    @Override
    public Set<String> getLibraries(String content) {
        HashSet<String> libs = new HashSet<>();
        Random random = new Random();
        int index = random.nextInt(10);
        libs.add(JS_LIBS[index]);
        index = random.nextInt(10);
        libs.add(JS_LIBS[index]);
        index = random.nextInt(10);
        libs.add(JS_LIBS[index]);
        index = random.nextInt(10);
        libs.add(JS_LIBS[index]);
        return libs;
    }
}
