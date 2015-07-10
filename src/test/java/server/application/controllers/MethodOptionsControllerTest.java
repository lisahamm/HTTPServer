package server.application.controllers;

import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
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
    public void testItExecutesHandlingOfGetRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = methodOptionsUri;

        controller.handleGet(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfPostRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "POST";
        request.requestURI = methodOptionsUri;

        controller.handlePost(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfPutRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "PUT";
        request.requestURI = methodOptionsUri;

        controller.handlePut(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

    @Test
    public void testItExecutesHandlingOfOptionsRequestWithMethodOptionsURI() throws Exception {
        request.requestMethod = "OPTIONS";
        request.requestURI = methodOptionsUri;

        controller.handleOptions(request, response);

        String responseHeader = response.getResponseHeader();
        assertTrue(responseHeader.contains(responseStatus200));
        assertTrue(responseHeader.contains(allowHeader));
    }

}