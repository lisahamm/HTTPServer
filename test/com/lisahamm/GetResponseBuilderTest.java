//package com.lisahamm;
//
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class GetResponseBuilderTest {
//    private Map<String, String> parsedComponents;
//    private HTTPRequest validRequest;
//    private HTTPRequest invalidRequest;
//
//    @Before
//    public void setUp() throws Exception {
//        parsedComponents = new HashMap<>();
//        parsedComponents.put("requestMethod", "GET");
//        parsedComponents.put("requestURI", "/");
//        parsedComponents.put("httpVersion", "HTTP/1.1");
//        validRequest = new HTTPRequest(parsedComponents);
//
//        parsedComponents = new HashMap<>();
//        parsedComponents.put("requestMethod", "GET");
//        parsedComponents.put("requestURI", "/foobar");
//        parsedComponents.put("httpVersion", "HTTP/1.1");
//        invalidRequest = new HTTPRequest(parsedComponents);
//    }
//
//    @Test
//    public void testResponseToValidRequest() throws Exception {
//        ResponseBuilder builder = new GetResponseBuilder();
//        assertTrue(builder.getResponse().isEmpty());
//        builder.constructStatusLine(validRequest);
//        assertEquals("HTTP/1.1 200 OK\r\n", builder.getResponse());
//        builder.constructHeaders(validRequest);
//        assertTrue(builder.getResponse().contains("Content-Type: "));
//        builder.constructBody(validRequest);
//        assertTrue(builder.getResponse().contains("\r\n\r\n"));
//    }
//
//    @Test
//    public void testResponseToInvalidRequestURI() throws Exception {
//        ResponseBuilder builder = new GetResponseBuilder();
//        assertTrue(builder.getResponse().isEmpty());
//        builder.constructStatusLine(invalidRequest);
//        assertEquals("HTTP/1.1 404 Not Found\r\n", builder.getResponse());
//        builder.constructHeaders(invalidRequest);
//        assertTrue(builder.getResponse().contains("Content-Type: "));
//        builder.constructBody(invalidRequest);
//        assertTrue(builder.getResponse().contains("\r\n\r\n"));
//    }
//}
