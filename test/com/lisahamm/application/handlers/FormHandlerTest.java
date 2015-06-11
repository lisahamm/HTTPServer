package com.lisahamm.application.handlers;

import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
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
        mockRequest.body = "data=fatcat";

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(mockRequest.getBody(), formHandler.getFormData());
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
        String getResponseBody = new String(responseToGetRequest.getBody(), "UTF-8");
        assertEquals(postRequest.getBody(), getResponseBody);
    }


    @Test
    public void testPUTRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "PUT";
        mockRequest.requestURI = formHandlerURI;
        mockRequest.body = "data=heathcliff";

        boolean handled = formHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(mockRequest.getBody(), formHandler.getFormData());
    }

    @Test
    public void testDELETERequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest postRequest = new MockHTTPRequest();
        postRequest.requestMethod = "POST";
        postRequest.requestURI = formHandlerURI;
        postRequest.body = "data=fatcat";
        MockHTTPRequest getRequest = new MockHTTPRequest();
        getRequest.requestMethod = "GET";
        getRequest.requestURI = formHandlerURI;
        ResponseBuilder responseToGetRequest1 = new ResponseBuilder();
        MockHTTPRequest deleteRequest = new MockHTTPRequest();
        deleteRequest.requestMethod = "DELETE";
        deleteRequest.requestURI = formHandlerURI;
        ResponseBuilder responseToDeleteRequest = new ResponseBuilder();
        ResponseBuilder responseToGetRequest2 = new ResponseBuilder();

        boolean handledPostRequest = formHandler.handle(postRequest, response);
        boolean handledGetRequest1 = formHandler.handle(getRequest, responseToGetRequest1);
        boolean handledDeleteRequest = formHandler.handle(deleteRequest, responseToDeleteRequest);
        boolean handledGetRequest2 = formHandler.handle(getRequest, responseToGetRequest2);

        assertTrue(handledPostRequest);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(handledGetRequest1);
        String getResponseBody = new String(responseToGetRequest1.getBody(), "UTF-8");
        assertEquals(postRequest.getBody(), getResponseBody);

        assertTrue(handledDeleteRequest);
        assertTrue(responseToDeleteRequest.getResponseHeader().contains(responseStatus200));
        assertTrue(handledGetRequest2);
        assertNull(responseToGetRequest2.getBody());
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "PATCH";
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