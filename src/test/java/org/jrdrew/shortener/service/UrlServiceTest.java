package org.jrdrew.shortener.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNull;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 8:05 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/shortener-servlet.xml")
public class UrlServiceTest {

    @Autowired
    private UrlService urlService;
    @Test
    public void testGetLongUrl() throws Exception {
        assertNull(urlService.getLongUrl("sdfsdf"));

    }

    @Test
    public void testGetShortUrl() throws Exception {
        assertNull(urlService.createShortUrl("http://www.example.com"));

    }
}
