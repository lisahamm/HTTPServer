package com.lisahamm.application.controllers;

import com.lisahamm.core.response.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class EatCookieHandlerTest {
    private String eatCookieHandlerURI = "/eat_cookie";
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private EatCookieHandler eatCookieHandler;

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
        eatCookieHandler = new EatCookieHandler();
    }

    @Test
    public void testItHandlesRequestWithCorrectURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = eatCookieHandlerURI;
        request.headers = new HashMap<>();
        request.headers.put("Cookie", "type=Y2hvY29sYXRl");

        boolean handled = eatCookieHandler.handle(request, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/html"));
        assertTrue(new String(response.getBody()).contains("<h1>mmmm " + "chocolate" + "</h1>"));
        assertTrue(new String(response.getBody()).contains("<img src=\"/cookie.png\">"));
    }

    @Test
    public void testItHandlesRequestWithInvalidMethod() throws Exception {
        request.requestMethod = "PUT";
        request.requestURI = eatCookieHandlerURI;

        boolean handled = eatCookieHandler.handle(request, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }
}