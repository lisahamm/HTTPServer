package server.application.controllers;

import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class StaticFileControllerTest {
    private MockResourceManager resourceManager;
    private StaticFileController controller;
    private MockHTTPRequest request;
    private Map<String, String> headers;
    private ResponseBuilder response;

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        controller = new StaticFileController(resourceManager);
        request = new MockHTTPRequest();
        headers = new HashMap<>();
        response = new ResponseBuilder();
    }

    @Test
    public void testItShouldExecuteWithARequestURIFoundInPublicDirectory() throws Exception {
        request.requestURI = "/file1";

        assertTrue(controller.shouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteWithRequestURINotFoundInPublicDirectory() throws Exception {
        request.requestURI = "/form2";

        assertFalse(controller.shouldExecute(request));
    }

    @Test
    public void testItExecutesHandlingOfRequestForFullFileInPublicDirectory() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        request.headers = headers;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(resourceManager.isFileRequested);
        assertEquals(new String(resourceManager.getFileContents("/file1")), new String(response.getBody()));
    }

    @Test
    public void testItExecutesHandlingOfRequestForPartialFileInPublicDirectory() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Range", "bytes=0-4");
        request.headers = headers;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 206 Partial Content\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(resourceManager.isPartialContentRequested);
    }

    @Test
    public void testItExecutesHandlingOfPatchRequest() throws Exception {
        request.requestMethod = "PATCH";
        request.requestURI = "/patch-content.txt";
        headers.put("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
        headers.put("Content-Length", "15");
        request.headers = headers;
        request.body = "patched content";

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 204 No Content"));
        assertTrue(response.getResponseHeader().contains("ETag"));
        assertNull(response.getBody());
    }
}