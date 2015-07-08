package server.application.controllers;

import server.application.Resources;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class LogsControllerTest {
    private MockResourceManager resourceManager;
    private LogsController controller;
    private MockHTTPRequest request;
    private Map<String, String> headers;
    private ResponseBuilder response;
    private String logsUri = "/logs";
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private String responseStatus401 = "HTTP/1.1 401 Unauthorized\r\n";

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        controller = new LogsController(resourceManager);
        request = new MockHTTPRequest();
        headers = new HashMap<>();
        response = new ResponseBuilder();
    }

    @Test
    public void testItShouldExecuteWithCorrectRequestURI() throws Exception {
        request.requestURI = logsUri;

        assertTrue(controller.shouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteWithIncorrectRequestURI() throws Exception {
        request.requestURI = "/root";

        assertFalse(controller.shouldExecute(request));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithValidAuthorizationCredentials() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        request.headers = headers;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains(Resources.logs.toString()));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithInvalidAuthorizationCredentials() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        headers.put("Authorization", "Basic YWRtaW46dGVzdA==");
        request.headers = headers;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus401));
        assertEquals("Authentication required", new String(response.getBody()));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithoutAuthorizationHeader() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        request.headers = headers;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus401));
        assertEquals("Authentication required", new String(response.getBody()));
    }
}