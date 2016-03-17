package org.jrdrew.shortener.controller;

import org.jrdrew.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 7:43 AM
 */
@Controller
@RequestMapping("/")
public class ShortenerController {

    private UrlService urlService;

    @Autowired
    public ShortenerController(UrlService urlService) {
        this.urlService = urlService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "{shortUrl}")
    public String redirectToLong(@PathVariable String shortUrl) throws IOException {
        return "redirect:" + urlService.getLongUrl(shortUrl);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shorten")
    @ResponseBody
    public String createShortUrl(@RequestParam String url) throws IOException {
        return urlService.createShortUrl(url);
    }
}
