package org.jrdrew.shortener.controller;

import org.jrdrew.shortener.service.UrlService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 7:08 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-servlet.xml")
public class ShortenerControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    private UrlService urlService = mock(UrlService.class);

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(new ShortenerController(urlService)).build();
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(urlService);
    }

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void testRedirectToLong() throws Exception {
        String expectedLongUrl = "http://www.example.com";
        String shortUrl = "abcd";
        when(urlService.getLongUrl(shortUrl)).thenReturn(expectedLongUrl);
        MvcResult mvcResult = this.mockMvc.perform(get("/" + shortUrl).accept(MediaType.APPLICATION_JSON)).andExpect(status().isMovedTemporarily()).andReturn();
        assertThat(mvcResult.getResponse().getRedirectedUrl(), equalTo(expectedLongUrl));
        verify(urlService, times(1)).getLongUrl(shortUrl);
    }

    @Test
    public void testCreateShortUrl() throws Exception {
        String inputtedLongUrl = "http://www.example.com";
        String shortUrl = "abcd";
        when(urlService.createShortUrl(inputtedLongUrl)).thenReturn(shortUrl);
        MvcResult mvcResult = this.mockMvc.perform(post("/shorten?url=" + inputtedLongUrl, "UTF-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getContentAsString(), equalTo(shortUrl));
        verify(urlService, times(1)).createShortUrl(inputtedLongUrl);
    }


}
