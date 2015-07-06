package com.lisahamm.application.handlers;

import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MethodOptionsControllerTest {
    private MethodOptionsController controller;
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private String methodOptionsUri = "/method_options";
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private String allowHeader = "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";

    @Before
    public void setUp() throws Exception {
        controller = new MethodOptionsController(new MockResourceManager());
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
    }

    @Test
    public void testItShouldExecuteWithCorrectRequestURI() throws Exception {
        request.requestURI = methodOptionsUri;

        assertTrue(controller.shouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteWithIncorrectRequestURI() throws Exception {
        request.requestURI = "/root";

        assertFalse(controller.shouldExecute(request));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = methodOptionsUri;

        controller.execute(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfPostRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "POST";
        request.requestURI = methodOptionsUri;

        controller.execute(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfPutRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "PUT";
        request.requestURI = methodOptionsUri;

        controller.execute(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfOptionsRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "OPTIONS";
        request.requestURI = methodOptionsUri;

        controller.execute(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

}