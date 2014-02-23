package org.jrdrew.shortener.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 7:43 AM
 */
@Controller
@RequestMapping("/")
public class ShortenerController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> get() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>("hello", responseHeaders, HttpStatus.OK);
    }
}
