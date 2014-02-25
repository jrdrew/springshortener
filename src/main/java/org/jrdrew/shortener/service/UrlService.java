package org.jrdrew.shortener.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 7:58 AM
 */
@Service
public class UrlService {

    private final Map<String, String> urlMap;

    public UrlService() {
        urlMap = new HashMap<String, String>();
    }

    public String getLongUrl(String shortUrl) {
        return urlMap.get(shortUrl);
    }

    public String createShortUrl(String longUrl) {

        if (StringUtils.isBlank(longUrl)) {
            throw new IllegalArgumentException("long url parameter cannot be null or blank");
        }
        String shortUrl = Integer.toString(urlMap.size());
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }
}
