package com.lisahamm.application.handlers;

import com.lisahamm.RequestHandler;
import com.lisahamm.ResourceManager;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LogHandlerTest {
    private RequestHandler logHandler;
    private MockHTTPRequest mockRequest;
    private Map<String, String> headers;
    private ResponseBuilder response;
    private ResourceManager resourceManager;
    private String responseStatus200 = "HTTP/1.1 200 OK";
    private String responseStatus401 = "HTTP/1.1 401 Unauthorized";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed";

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        logHandler = new LogHandler(resourceManager);
        headers = new HashMap<>();
        response = new ResponseBuilder();
    }

    @Test
    public void testItHandlesAGetRequestWithoutAuthorizationHeader() throws Exception {
        mockRequest = generateRequest("GET", "/logs", headers);

        boolean handled = logHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus401));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("Authentication required"));
    }

    @Test
    public void testItHandlesAGetRequestWithInvalidAuthorizationCredentials() throws Exception {
        headers.put("Authorization", "Basic YWRtaW46dGVzdA==");
        mockRequest = generateRequest("GET", "/logs", headers);

        boolean handled = logHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus401));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("Authentication required"));
    }

    @Test
    public void testItHandlesAGetRequestWithValidAuthorizationCredentials() throws Exception {
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        mockRequest = generateRequest("GET", "/logs", headers);

        boolean handled = logHandler.handle(mockRequest, response);

        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus200));
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains(resourceManager.retrieveData("/logs")));
    }


    @Test
    public void testRequestWithInvalidMethodAndCorrectURIIsHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "DELETE";
        mockRequest.requestURI = "/logs";
        boolean handled = logHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRequestWithInvalidURIIsNotHandled() throws Exception {
        MockHTTPRequest mockRequest = new MockHTTPRequest();
        mockRequest.requestMethod = "GET";
        mockRequest.requestURI = "/log";
        boolean handled = logHandler.handle(mockRequest, response);
        assertFalse(handled);
    }

    private MockHTTPRequest generateRequest(String method, String uri, Map<String, String> headers) {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = method.toUpperCase();
        request.requestURI = uri;
        request.headers = headers;
        return request;
    }
}
