package com.lisahamm.core.requests;

import com.lisahamm.core.requests.HTTPRequest;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;


public class HTTPRequestTest {
    private HTTPRequest request;
    private Map<String, String> parsedComponents;
    private Map<String, String> params;
    private Map<String, String> headers;

    @Before
    public void setUp() throws Exception {
        parsedComponents = new HashMap<>();
        parsedComponents.put("requestMethod", "GET");
        parsedComponents.put("requestURI", "/");
        parsedComponents.put("httpVersion", "HTTP/1.1");
        params = new HashMap<>();
        params.put("variable1Key", "variable1Value");
        headers = new HashMap<>();
        request = new HTTPRequest(parsedComponents, params, headers);
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
        assertEquals(headers, request.getHeaders());
    }

    @Test
    public void testRequestBodyIsSet() throws Exception {
        assertEquals(parsedComponents.get("body"), request.getBody());
    }

    @Test
    public void testRequestParamsAreSet() throws Exception {
        assertEquals(params, request.getParams());
    }
}
