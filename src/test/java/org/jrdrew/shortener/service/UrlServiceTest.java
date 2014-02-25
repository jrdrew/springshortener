package org.jrdrew.shortener.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 8:05 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
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

    @Test
    public void testGetShortUrlDifferentForDifferentUrls() throws Exception {
        String longUrl = "http://www.example.com";
        String shortUrl = urlService.createShortUrl(longUrl);

        String longUrl2 = "http://www.example.com/example";
        String shortUrl2 = urlService.createShortUrl(longUrl2);

        assertThat(shortUrl, not(equalTo(shortUrl2)));
    }

    @Test
    public void testGetShortUrlSameUrlMultipleTimes() throws Exception {
        String longUrl = "http://www.example.com";
        String shortUrl1 = urlService.createShortUrl(longUrl);

        String shortUrl2 = urlService.createShortUrl(longUrl);

        assertThat(shortUrl1, equalTo(shortUrl2));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShortUrlNull() throws Exception {
        urlService.createShortUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShortUrlEmpty() throws Exception {
        urlService.createShortUrl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateShortUrlBlank() throws Exception {
        urlService.createShortUrl(" ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongUrlNull() throws Exception {
        urlService.getLongUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongUrlEmpty() throws Exception {
        urlService.getLongUrl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLongUrlBlank() throws Exception {
        urlService.getLongUrl(" ");
    }
}
