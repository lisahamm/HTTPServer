
package com.lisahamm.application.handlers;

import com.lisahamm.*;
import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FileHandlerTest {
    private MockResourceManager resourceManager;
    private RequestHandler fileHandler;
    private ResponseBuilder response;
    private Map<String, String> headers;

    @Before
    public void setUp() throws Exception {
        headers = new HashMap<>();
        response = new ResponseBuilder();
        resourceManager = new MockResourceManager();
        fileHandler = new FileHandler(resourceManager);
    }

    @Test
    public void testItHandlesRequestForFullFileInPublicDirectory() throws Exception {
        Request validRequest = generateRequest("GET", "/file1", headers);

        boolean isHandled = fileHandler.handle(validRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(response.getBody().length > 0);
        assertTrue(isHandled);
    }

    @Test
    public void testItHandlesRequestForPartialFileContentsInPublicDirectory() throws Exception {
        headers.put("Range", "bytes=0-4");
        Request validRequest = generateRequest("GET", "/file1", headers);

        boolean isHandled = fileHandler.handle(validRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 206 Partial Content\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(resourceManager.isPartialContentRequested);
        assertTrue(isHandled);
    }

    @Test
    public void testHandlesPatchRequest() throws Exception {
        headers.put("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
        headers.put("Content-Length", "15");
        String body = "patched content";
        Request validPatchRequest = generateRequestWithBody("PATCH", "/patch-content.txt", headers, body);

        boolean isHandled = fileHandler.handle(validPatchRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 204 No Content"));
        assertTrue(response.getResponseHeader().contains("ETag"));
        assertNull(response.getBody());
        assertTrue(isHandled);
    }

    @Test
    public void testItHandlesRequestForMethodNotAllowed() throws Exception {
        Request invalidRequest = generateRequest("POST", "/file1", headers);

        boolean isHandled = fileHandler.handle(invalidRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 405 Method Not Allowed"));
        assertTrue(isHandled);
    }

    @Test
    public void testDoesNotHandleRequestIfUriDoesNotExistInDirectory() throws Exception {
        Request invalidRequest = generateRequest("GET", "/file1000", headers);
        boolean isHandled = fileHandler.handle(invalidRequest, response);
        assertFalse(isHandled);
    }

    private MockHTTPRequest generateRequest(String method, String uri, Map<String, String> headers) {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = method.toUpperCase();
        request.requestURI = uri;
        request.headers = headers;
        return request;
    }

    private MockHTTPRequest generateRequestWithBody(String method, String uri, Map<String, String> headers, String body) {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = method.toUpperCase();
        request.requestURI = uri;
        request.headers = headers;
        request.body = body;
        return request;
    }
}
