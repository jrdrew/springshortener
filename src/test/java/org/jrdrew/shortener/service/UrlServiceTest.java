package org.jrdrew.shortener.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void testGetLongUrlNotPreviouslySet() throws Exception {
        assertNull(urlService.getLongUrl("sdfsdf"));

    }

    @Test
    public void testGetShortUrl() throws Exception {
        String longUrl = "http://www.example.com";
        String shortUrl = urlService.createShortUrl(longUrl);
        assertNotNull(shortUrl);
        assertThat(urlService.getLongUrl(shortUrl), equalTo(longUrl));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortUrlNull() throws Exception {
        urlService.createShortUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortUrlEmpty() throws Exception {
        urlService.createShortUrl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShortUrlBlank() throws Exception {
        urlService.createShortUrl(" ");
    }
}
