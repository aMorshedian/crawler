package org.gsearch.crawl.google.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleSearchResponseProcessor implements Function<String, GoogleSearchResponse> {

    public static final String URL_REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    public static final String BASE_URL_REGEX = ".*//(.*)/";

    @Override
    public GoogleSearchResponse apply(String response) {
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(response);
        Set<String> searchResults = new HashSet<>();
        while (matcher.find()) {
                String linkRes = matcher.group();

                Pattern basePattern = Pattern.compile(BASE_URL_REGEX);
                Matcher baseMatcher = basePattern.matcher(linkRes);
                if (baseMatcher.find()){
                    searchResults.add( linkRes.substring(0,
                            linkRes.indexOf("/",linkRes.indexOf("//")+2)).trim());
                }else {
                    searchResults.add(linkRes.toLowerCase().trim());
                }
        }
        return new GoogleSearchResponse(searchResults);
    }
}
