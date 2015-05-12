package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetRequestHandlerTest {
    private Map<String, String> parsedComponents;
    private HTTPRequest validRequest;
    private HTTPRequest invalidRequest;

    @Before
    public void setUp() throws Exception {
        parsedComponents = new HashMap<>();
        parsedComponents.put("requestMethod", "GET");
        parsedComponents.put("requestURI", "/");
        parsedComponents.put("httpVersion", "HTTP/1.1");
        validRequest = new HTTPRequest(parsedComponents);

        parsedComponents = new HashMap<>();
        parsedComponents.put("requestMethod", "GET");
        parsedComponents.put("requestURI", "/foobar");
        parsedComponents.put("httpVersion", "HTTP/1.1");
        invalidRequest = new HTTPRequest(parsedComponents);
    }

    @Test
    public void testResponseToValidRequest() throws Exception {
        RequestHandler handler = new GetRequestHandler(new GetResponseBuilder());
        handler.handle(validRequest);
        assertTrue(handler.getResponse().contains("HTTP/1.1"));
        assertTrue(handler.getResponse().contains("200"));
        assertTrue(handler.getResponse().contains("OK"));
    }

    @Test
    public void testResponseToInvalidRequestURI() throws Exception {
        RequestHandler handler = new GetRequestHandler(new GetResponseBuilder());
        handler.handle(invalidRequest);
        assertTrue(handler.getResponse().contains("HTTP/1.1"));
        assertTrue(handler.getResponse().contains("404"));
        assertTrue(handler.getResponse().contains("Not Found"));
    }

}
