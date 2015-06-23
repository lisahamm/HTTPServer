package com.lisahamm.application.handlers;

import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CookieHandlerTest {
    private String cookieHandlerURI = "/cookie";
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private CookieHandler cookieHandler;

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
        cookieHandler = new CookieHandler();
    }

    @Test
    public void testItHandlesRequestWithCorrectURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = cookieHandlerURI;

        boolean handled = cookieHandler.handle(request, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testItHandlesRequestWithInvalidMethod() throws Exception {
        request.requestMethod = "PUT";
        request.requestURI = cookieHandlerURI;

        boolean handled = cookieHandler.handle(request, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testItDoesNotHandleRequestWithIncorrectURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/cookie_time";

        boolean handled = cookieHandler.handle(request, response);

        assertFalse(handled);
    }
}
