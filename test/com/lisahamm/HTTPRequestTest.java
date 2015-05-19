package com.lisahamm;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;


public class HTTPRequestTest {
    private HTTPRequest request;
    private Map<String, String> parsedComponents;
    private Map<String, String> params;

    @Before
    public void setUp() throws Exception {
        parsedComponents = new HashMap<>();
        parsedComponents.put("requestMethod", "GET");
        parsedComponents.put("requestURI", "/");
        parsedComponents.put("httpVersion", "HTTP/1.1");
        params = new HashMap<>();
        params.put("variable1Key", "variable1Value");
        request = new HTTPRequest(parsedComponents, params);
    }

    @Test
    public void testHTTPRequestMethodIsSet() throws Exception {
        assertEquals(parsedComponents.get("requestMethod"), request.getRequestMethod());
    }

    @Test
    public void testHTTPRequestUriIsSet() throws Exception {
        assertEquals(parsedComponents.get("requestURI"), request.getRequestURI());
    }

    @Test
    public void testRequestHTTPVersionIsSet() throws Exception {
        assertEquals(parsedComponents.get("httpVersion"), request.getHTTPVersion());
    }

    @Test
    public void testRequestHeadersStringIsSet() throws Exception {
        assertEquals(parsedComponents.get("headers"), request.getHeaders());
    }

    @Test
    public void testRequestBodyIsSet() throws Exception {
        assertEquals(parsedComponents.get("body"), request.getBody());
    }

    @Test
    public void testRequestParamsAreSet() throws Exception {
        assertEquals(params.get("variable1Key"), request.getParams().get("variable1Key"));
    }
}
