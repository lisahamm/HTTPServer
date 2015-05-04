package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {
    private String getRequest = "GET / HTTP/1.1";
    private String postRequest = "POST / HTTP/1/1";

    @Test
    public void testHandlesGetRequest() {
        RequestHandler requestHandler = new RequestHandler(new MockRequestParser(getRequest));
        String response = requestHandler.getResponse();
        assertEquals("HTTP/1.1 200 OK\r\n", response);
    }

    @Test
    public void testHandlesPostRequest() {
        RequestHandler requestHandler = new RequestHandler(new MockRequestParser(postRequest));
        String response = requestHandler.getResponse();
        assertEquals("HTTP/1.1 200 OK\r\n", response);
    }


    public class MockRequestParser implements Parser {
        public String request;
        public String requestLine;

        public MockRequestParser(String request) {
            this.request = request;
        }

        public void parseRequest() {
            this.requestLine = request;
        }
    }
}
