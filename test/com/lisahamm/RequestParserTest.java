package com.lisahamm;
import org.junit.*;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestParserTest {

    @Test
//    public void testParseRequestWithOnlyRequestLine() throws Exception {
//        MockRequest mockRequest = new MockRequest("GET / HTTP/1.1\r\n");
//        RequestParser requestParser = new RequestParser();
//        requestParser.parseRequest(mockRequest);
//        assertEquals(true, mockRequest.setWithParsedComponents);
//    }
//
//    @Test
//    public void testParseRequestWithHeader() throws Exception {
//        String httpRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0";
//        RequestParser requestParser = new RequestParser(httpRequest);
//        requestParser.parseRequest();
//        assertEquals("GET / HTTP/1.1", requestParser.getRequestLine());
//        assertEquals("Host: 0.0.0.0", requestParser.getHeaders());
//    }
////
//    @Test
//    public void testParseRequestWithBody() throws Exception {
//        String httpRequest = "GET / HTTP/1.1\r\n\r\nBody";
//        RequestParser requestParser = new RequestParser(httpRequest);
//        requestParser.parseRequest();
//        assertEquals("GET / HTTP/1.1", requestParser.getRequestLine());
//        assertEquals("Body", requestParser.getBody());
//    }
//
//    @Test
//    public void testParseRequestWithHeaderAndBody() throws Exception {
//        String httpRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\n\r\nBody";
//        RequestParser requestParser = new RequestParser(httpRequest);
//        requestParser.parseRequest();
//        assertEquals("GET / HTTP/1.1", requestParser.getRequestLine());
//        assertEquals("Host: 0.0.0.0", requestParser.getHeaders());
//        assertEquals("Body", requestParser.getBody());
//    }
//
//    @Test
//    public void testParseRequestWithMultipleHeaders() throws Exception {
//        String httpRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\nContent-Type: text/plain\r\n\r\nBody";
//        RequestParser requestParser = new RequestParser(httpRequest);
//        requestParser.parseRequest();
//        assertEquals("GET / HTTP/1.1", requestParser.getRequestLine());
//        assertTrue(requestParser.getHeaders().contains("Host: 0.0.0.0"));
//        assertTrue(requestParser.getHeaders().contains("Content-Type: text/plain"));
//        assertEquals("Body", requestParser.getBody());
//    }
}
