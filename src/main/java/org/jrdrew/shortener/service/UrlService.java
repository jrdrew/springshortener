package org.jrdrew.shortener.service;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 7:58 AM
 */
@Service
public class UrlService {

    private final BidiMap urlMap;

    public UrlService() {
        urlMap = new DualHashBidiMap();
    }

    public String getLongUrl(String shortUrl) {
        if (StringUtils.isBlank(shortUrl)) {
            throw new IllegalArgumentException("short url parameter cannot be null or blank");
        }
        return (String) urlMap.get(shortUrl);
    }

    public String createShortUrl(String longUrl) {

        if (StringUtils.isBlank(longUrl)) {
            throw new IllegalArgumentException("long url parameter cannot be null or blank");
        }

        if (urlMap.containsValue(longUrl)) {
            return (String) urlMap.getKey(longUrl);
        }

        String shortUrl = generateShortUrl((long) urlMap.size());
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    protected String generateShortUrl(long key) {

        Double num = (double) key;
        String charSet = "23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        Integer length = charSet.length();
        String encodeString = "";
        while (num >= length) {
            encodeString = charSet.charAt(num.intValue() % length) + encodeString;
            num = Math.ceil(num / length - 1);
        }
        encodeString = charSet.charAt(num.intValue()) + encodeString;

        return encodeString;
    }
}
