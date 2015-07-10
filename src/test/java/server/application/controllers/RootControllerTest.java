package server.application.controllers;

import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RootControllerTest {
    private MockResourceManager resourceManager;
    private RootController controller;
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private String rootUri = "/";
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private static String contentTypeHTML = "Content-Type: text/html";

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        controller = new RootController(resourceManager);
        request = new MockHTTPRequest();
        request.requestMethod = "GET";
        request.requestURI = rootUri;
        response = new ResponseBuilder();
    }

    @Test
    public void testSuccessfullyExecutesHandlingOfGetRequestWithRootURI() throws Exception {
        controller.handleGet(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testAddsContentTypeHtmlHeader() throws Exception {
        controller.handleGet(request, response);

        assertTrue(response.getResponseHeader().contains(contentTypeHTML));
    }

    @Test
    public void testAddsBodyWithLinksToPublicDirectoryContents() throws Exception {
        controller.handleGet(request, response);

        String responseBody = new String(response.getBody());
        List<String> publicDirectoryContent = resourceManager.getPublicDirectoryContents();
        String publicFile = publicDirectoryContent.get(0);
        assertTrue(responseBody.contains("<a href=\"/" + publicFile + "\">" + publicFile + "</a>"));
    }
}