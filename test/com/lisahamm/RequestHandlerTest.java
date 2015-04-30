package com.lisahamm;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {

    @Test
    public void testParseRequestWithOnlyRequestLine() throws Exception {
        String httpRequest = "GET / HTTP/1.1";
        RequestHandler requestHandler = new RequestHandler(httpRequest);
        requestHandler.parseRequest();
        assertEquals(httpRequest, requestHandler.getRequestLine());
    }
}
