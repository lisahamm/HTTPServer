package com.lisahamm;
import org.junit.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestParserTest {
    private RequestParser requestParser;
    private String rawRequest;
    private String rawRequestEncoded;

    @Before
    public void setUp() throws Exception {
        requestParser = new RequestParser();
        rawRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\n\r\nBody";
        rawRequestEncoded = "GET /file1%20copy HTTP/1.1\r\nHost: 0.0.0.0\r\n";
    }

    @Test
    public void testRequestMethodIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("GET", request.getRequestMethod());
    }

    @Test
    public void testRequestUriIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("/", request.getRequestURI());
    }

    @Test
    public void testEncodedRequestUriIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequestEncoded);
        assertEquals("/file1 copy", request.getRequestURI());
    }

    @Test
    public void testRequestHTTPVersionIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("HTTP/1.1", request.getHTTPVersion());
    }

    @Test
    public void testRequestBodyIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("Body", request.getBody());
    }

    @Test
    public void testRequestHeaderIsParsed() throws Exception {
        HTTPRequest request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("Host: 0.0.0.0", request.getHeaders());
    }
}
