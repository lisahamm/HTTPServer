package com.lisahamm.application.handlers;

import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormHandlerTest {
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private String formHandlerURI = "/form";
    private ResponseBuilder response;
    private FormHandler formHandler;
    private MockResourceManager resourceManager;
    private MockHTTPRequest mockRequest;

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        mockRequest = new MockHTTPRequest();
        response = new ResponseBuilder();
        formHandler = new FormHandler(resourceManager);
    }

    @Test
    public void testGETRequestWithCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = formHandlerURI;

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        String resourceData = resourceManager.retrieveData(mockRequest.getRequestURI());
        String responseBody = new String(response.getBody());
        assertEquals(resourceData, responseBody);
    }

    @Test
    public void testPOSTRequestWithCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "POST";
        mockRequest.requestURI = formHandlerURI;
        mockRequest.body = "data=fatcat";

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(mockRequest.getBody(), resourceManager.retrieveData(mockRequest.getRequestURI()));
    }

    @Test
    public void testPOSTThenGETRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest postRequest = new MockHTTPRequest();
        postRequest.requestMethod = "POST";
        postRequest.requestURI = formHandlerURI;
        postRequest.body = "data=fatcat";
        MockHTTPRequest getRequest = new MockHTTPRequest();
        getRequest.requestMethod = "GET";
        getRequest.requestURI = formHandlerURI;
        ResponseBuilder responseToGetRequest = new ResponseBuilder();

        boolean handledPostRequest = formHandler.handle(postRequest, response);
        boolean handledGetRequest = formHandler.handle(getRequest, responseToGetRequest);

        assertTrue(handledPostRequest);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(handledGetRequest);
        String getResponseBody = new String(responseToGetRequest.getBody());
        assertEquals(postRequest.getBody(), getResponseBody);
    }


    @Test
    public void testPUTRequestWithCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "PUT";
        mockRequest.requestURI = formHandlerURI;
        mockRequest.body = "data=heathcliff";

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(mockRequest.getBody(), resourceManager.retrieveData(mockRequest.getRequestURI()));
    }

    @Test
    public void testDELETERequestWithCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = formHandlerURI;

        boolean handledDeleteRequest = formHandler.handle(mockRequest, response);

        assertTrue(handledDeleteRequest);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(resourceManager.retrieveData(mockRequest.getRequestURI()).length() == 0);
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "PATCH";
        mockRequest.requestURI = formHandlerURI;

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/form2";

        boolean handled = formHandler.handle(mockRequest, response);

        assertFalse(handled);
    }
}
