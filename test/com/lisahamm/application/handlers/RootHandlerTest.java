package com.lisahamm.application.handlers;

import com.lisahamm.AppFileManager;
import com.lisahamm.mocks.MockFileManager;
import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RootHandlerTest {
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private String rootHandlerURI = "/";
    private static String contentTypeHTML = "Content-Type: text/html";
    private MockHTTPRequest mockRequest;
    private ResponseBuilder response;
    private MockResourceManager resourceManager;
    private RootHandler rootHandler;

    @Before
    public void setUp() throws Exception {
        mockRequest = new MockHTTPRequest();
        response = new ResponseBuilder();
        resourceManager = new MockResourceManager();
        rootHandler = new RootHandler(resourceManager);
    }

    @Test
    public void testGETRequestWithCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = rootHandlerURI;

        boolean handled = rootHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(contentTypeHTML));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("<a href="));
        assertTrue(responseBody.contains(resourceManager.getPublicDirectoryContents().get(0)));
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = rootHandlerURI;

        boolean handled = rootHandler.handle(mockRequest, response);

        Assert.assertTrue(handled);
        Assert.assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/root";

        boolean handled = rootHandler.handle(mockRequest, response);

        assertFalse(handled);
    }


}
