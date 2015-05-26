package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RedirectHandlerTest {
    private RequestHandler redirectHandler;
    private ResponseBuilder response;
    private Request request;

    @Before
    public void setUp() throws Exception {
        request = new MockRequest();
        response = new ResponseBuilder();
        redirectHandler = new RedirectHandler();
    }

    @Test
    public void testItAdds200StatusLineToResponseWhenRequestIsValid() throws Exception {
        assertEquals("", response.getResponseHeader());
        redirectHandler.handle(request, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 302 Found"));
    }

    @Test
    public void testItAddsLocationHeaderToResponseWhenRequestIsValid() throws Exception {
        assertEquals("", response.getResponseHeader());
        redirectHandler.handle(request, response);
        assertTrue(response.getResponseHeader().contains("Location: http://localhost:5000/"));
    }

    private class MockRequest implements Request {
        public String getRequestMethod() {
            return "GET";
        }

        public String getRequestURI() {
            return "/redirect";
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
            return null;
        }
    }
}
