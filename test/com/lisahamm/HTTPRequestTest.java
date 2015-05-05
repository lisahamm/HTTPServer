package com.lisahamm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by lisahamm on 5/4/15.
 */
public class HTTPRequestTest {
    @Test
    public void testHTTPRequestIsParsed() throws Exception {
        String rawRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\n\r\nBody";
        Parser requestParser = new RequestParser();
        HTTPRequest httpRequest = new HTTPRequest(rawRequest, requestParser);
        httpRequest.parse();
        assertEquals("GET", httpRequest.getRequestMethod());
    }
}
