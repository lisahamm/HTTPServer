package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HTTPResponseTest {
    private Map<String, String> responseComponents;

    @Before
    public void setUp() throws Exception {
        responseComponents = new HashMap<>();
        responseComponents.put("httpVersion", "HTTP/1.1");
        responseComponents.put("responseCode", "200");
        responseComponents.put("responsePhrase", "OK");
        responseComponents.put("headers", "Content-Type: text/plain");
        responseComponents.put("body", "This is the body.");
    }

    @Test
    public void testResponseLineIsConstructed() throws Exception {
        HTTPResponse response = new HTTPResponse(responseComponents);
        response.build();
        assertTrue(response.getResponse().contains("HTTP/1.1"));
        assertTrue(response.getResponse().contains("200"));
        assertTrue(response.getResponse().contains("OK"));
    }

    @Test
    public void testResponseIncludesHeaders() throws Exception {
        HTTPResponse response = new HTTPResponse(responseComponents);
        response.build();
        assertTrue(response.getResponse().contains("Content-Type: text/plain"));
    }

    @Test
    public void testResponseIncludesBody() throws Exception {
        HTTPResponse response = new HTTPResponse(responseComponents);
        response.build();
        assertTrue(response.getResponse().contains("This is the body."));
    }
}
