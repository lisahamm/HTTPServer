package com.lisahamm.application.handlers;

import com.lisahamm.DirectoryManager;
import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.ResponseBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RootHandlerTest {
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";
    private String rootHandlerURI = "/";
    private static String contentTypeHTML = "Content-Type: text/html";
    private ResponseBuilder response;
    private RootHandler rootHandler;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
        DirectoryManager fileManager = new MockFileManager();
        rootHandler = new RootHandler(fileManager);
    }

    @Test
    public void testGETRequestWithCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = rootHandlerURI;
        boolean handled = rootHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(contentTypeHTML));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("<a href="));
    }

    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = rootHandlerURI;
        boolean handled = rootHandler.handle(mockRequest, response);
        Assert.assertTrue(handled);
        Assert.assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/root";
        boolean handled = rootHandler.handle(mockRequest, response);
        assertFalse(handled);
    }

    private class MockFileManager implements DirectoryManager {
        public List<String> buildDirectoryContents() {
            List<String> directoryContents = new ArrayList<>();
            directoryContents.add("file1");
            directoryContents.add("file2");
            return directoryContents;
        }
    }


}
