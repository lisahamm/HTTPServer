package server.application.controllers;

import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class RedirectControllerTest {
    private RedirectController controller;
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private String redirectUri = "/redirect";
    private String responseStatus302 = "HTTP/1.1 302 Found\r\n";
    private String locationHeader = "Location: http://localhost:5000/\r\n";

    @Before
    public void setUp() throws Exception {
        controller = new RedirectController(new MockResourceManager());
        request = new MockHTTPRequest();
        request.requestMethod = "GET";
        request.requestURI = redirectUri;
        response = new ResponseBuilder();
    }

    @Test
    public void testAdds302FoundStatusToResponseToGetRequestWithRedirectURI() throws Exception {
        controller.handleGet(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus302));
    }

    @Test
    public void testAddsLocationHeaderToResponseToGetRequestWithRedirectURI() throws Exception {
        controller.handleGet(request, response);

        assertTrue(response.getResponseHeader().contains(locationHeader));
    }

}