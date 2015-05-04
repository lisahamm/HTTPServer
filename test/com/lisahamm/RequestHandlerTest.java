package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

public class RequestHandlerTest {

    @Before
    public void setUp() throws Exception {
        RequestHandler requestHandler = new RequestHandler(new MockRequestParser());

    }

    @Test
    public void testHandlesGetRequest() {

    }

    public class MockRequestParser implements Parser {
        public String requestLine;

        public void parseRequest() {
            this.requestLine = "GET / HTTP/1.1";
        }
    }
}
