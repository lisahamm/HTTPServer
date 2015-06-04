package com.lisahamm.application.handlers;


import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.application.handlers.ParametersHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParametersHandlerTest {
    private RequestHandler parametersHandler;
    private ResponseBuilder response;
    private Request request;

    @Before
    public void setUp() throws Exception {
        request = new MockRequest();
        response = new ResponseBuilder();
        parametersHandler = new ParametersHandler();
    }

    @Test
    public void testItAddsStatusLineToResponse() throws Exception {
        assertEquals("", response.getResponseHeader());
        parametersHandler.handle(request, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK"));
    }

    @Test
    public void testItAddsContentTypeOfPlainTextToResponse() throws Exception {
        assertEquals("", response.getResponseHeader());
        parametersHandler.handle(request, response);
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain"));
    }

    @Test
    public void testItAddsRequestParametersToResponseBody() throws Exception {
        assertTrue(response.getBody() == null);
        parametersHandler.handle(request, response);
        String responseBody = new String(response.getBody());
        assertTrue(responseBody.contains("variable1 = parameter 1"));
    }

    private class MockRequest implements Request {
        public String getRequestMethod() {
            return "GET";
        }

        public String getRequestURI() {
            return "/parameters";
        }

        public String getHTTPVersion() {
            return "HTTP/1.1";
        }

        public String getHeaders() {
            return null;
        }

        public String getBody() {
            return null;
        }

        public Map<String, String> getParams() {
            Map<String,String> params = new HashMap();
            params.put("variable1", "parameter 1");
            return params;
        }
    }
}
