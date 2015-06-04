package com.lisahamm.application.handlers;

import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormHandlerTest {
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private String formHandlerURI = "/form";
    private ResponseBuilder response;
    private FormHandler formHandler;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
        formHandler = new FormHandler();
    }

    @Test
    public void testGETRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = formHandlerURI;
        boolean handled = formHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testPOSTRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "POST";
        mockRequest.requestURI = formHandlerURI;
        boolean handled = formHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testPUTRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "PUT";
        mockRequest.requestURI = formHandlerURI;
        boolean handled = formHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = formHandlerURI;
        boolean handled = formHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/form2";
        boolean handled = formHandler.handle(mockRequest, response);
        assertFalse(handled);
    }
}
