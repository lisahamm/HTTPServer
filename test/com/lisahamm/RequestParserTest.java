package com.lisahamm;
import org.junit.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestParserTest {
    private RequestParser requestParser;
    private String rawRequest;
    private String rawRequestEncoded;
    private String requestUriWithParams;
    private String requestWithOneParam;

    @Before
    public void setUp() throws Exception {
        requestParser = new RequestParser();
        rawRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\n\r\nBody";
        rawRequestEncoded = "GET /file1%20copy HTTP/1.1\r\nHost: 0.0.0.0\r\n";
        requestWithOneParam = "GET /parameters?variable_1=Operators";
        requestUriWithParams = "GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1";
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

    @Test
    public void testRequestWithParamsIsParsed() throws Exception {
        String uriWithOneParam = "/parameters?variable_1=Operators";
        String params = requestParser.getRequestParams(uriWithOneParam);
        assertEquals("variable_1=Operators", params);


    }
}
