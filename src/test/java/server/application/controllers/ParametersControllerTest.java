package server.application.controllers;

import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParametersControllerTest {
    private ParametersController controller;
    private ResponseBuilder response;
    private MockHTTPRequest request;
    private String parametersUri = "/parameters";
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private String contentTypeTextPlain = "Content-Type: text/plain";

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
        controller = new ParametersController(new MockResourceManager());
    }

    @Test
    public void testItShouldExecuteWithCorrectRequestURI() throws Exception {
        request.requestURI = parametersUri;

        assertTrue(controller.shouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteWithIncorrectRequestURI() throws Exception {
        request.requestURI = "/";

        assertFalse(controller.shouldExecute(request));
    }

    @Test
    public void testItExecutesHandlingOfGetRequestWithParametersURI() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = parametersUri;
        Map<String,String> params = new HashMap();
        params.put("variable1", "parameter 1");
        request.params = params;

        controller.execute(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
        assertTrue(response.getResponseHeader().contains(contentTypeTextPlain));

        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("variable1 = parameter 1"));
    }
}