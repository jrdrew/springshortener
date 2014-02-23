package org.jrdrew.shortener;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: jonathandrew
 * Date: 2/23/14
 * Time: 6:59 AM
 */
public class HelloWorldTest {

    @Test
    public void canary() {
        assertThat(true, equalTo(true));
    }
}
