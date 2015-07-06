package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class RedirectControllerTest {
    private RedirectController controller;
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private String redirectUri = "/redirect";
    private String responseStatus302 = "HTTP/1.1 302 Found\r\n";
    private String locationHeader = "Location: http://localhost:5000/\r\n";

    @Before
    public void setUp() throws Exception {
        controller = new RedirectController();
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
    }

    @Test
    public void testItShouldExecuteWithCorrectRequestURI() throws Exception {
        request.requestURI = redirectUri;

        assertTrue(controller.shouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteWithIncorrectRequestURI() throws Exception {
        request.requestURI = "/form";

        assertFalse(controller.shouldExecute(request));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithFormURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = redirectUri;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus302));
        assertTrue(response.getResponseHeader().contains(locationHeader));
    }
}