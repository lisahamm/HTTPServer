package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MethodOptionsHandlerTest {
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private String allowHeader = "Allow: GET,HEAD,POST,OPTIONS,PUT";
    private ResponseBuilder response;
    private MethodOptionsHandler methodOptionsHandler;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
        methodOptionsHandler = new MethodOptionsHandler();
    }

    @Test
    public void testGETRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/method_options";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(allowHeader));
    }

    @Test
    public void testPOSTRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "POST";
        mockRequest.requestURI = "/method_options";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(allowHeader));
    }

    @Test
    public void testPUTRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "POST";
        mockRequest.requestURI = "/method_options";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(allowHeader));
    }

    @Test
    public void testOptionsRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "OPTIONS";
        mockRequest.requestURI = "/method_options";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(allowHeader));
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = "/method_options";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
        assertTrue(response.getResponseHeader().contains(allowHeader));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/method";
        boolean handled = methodOptionsHandler.handle(mockRequest, response);
        assertFalse(handled);
    }

}