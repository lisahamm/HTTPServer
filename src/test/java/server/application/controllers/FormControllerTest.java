package server.application.controllers;

import server.application.Resources;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class FormControllerTest {
    private MockResourceManager resourceManager;
    private FormController controller;
    private MockHTTPRequest request;
    private ResponseBuilder response;
    private String formUri = "/form";
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";

    @Before
    public void setUp() throws Exception {
        resourceManager = new MockResourceManager();
        controller = new FormController(resourceManager);
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithFormURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = formUri;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(Resources.form, new String(response.getBody()));
    }

    @Test
    public void testItExecutesHandlingOfPostRequestWithFormURI() throws Exception {
        request.requestMethod = "POST";
        request.requestURI = formUri;
        request.body = "data=fatcat";

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));

        assertEquals(request.getBody(), Resources.form);
    }

    @Test
    public void testItExecutesHandlingOfPutRequestWithFormURI() throws Exception {
        request.requestMethod = "PUT";
        request.requestURI = formUri;
        request.body = "data=heathcliff";

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals(request.getBody(), Resources.form);
    }

    @Test
    public void testItExecutesHandlingOfDeleteRequestWithFormURI() throws Exception {
        request.requestMethod = "DELETE";
        request.requestURI = formUri;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertEquals("", Resources.form);
    }
}