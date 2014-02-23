package org.jrdrew.shortener.controller;

import org.jrdrew.shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public ResponseEntity<String> get() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>("hello", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{shortUrl}")
    public String redirectToLong(@PathVariable String shortUrl) throws IOException {
        return "redirect:" + urlService.getLongUrl(shortUrl);
    }
}
