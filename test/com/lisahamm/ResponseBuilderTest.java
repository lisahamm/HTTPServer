package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    private String status200 = "200";
    private String response200 = "HTTP/1.1 200 OK\r\n";
    private String htmlContentHeader = "Content-Type: text/html";
    private String responseHeader200 = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";
    private String body = "This is the body";
    private ResponseBuilder response;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
    }

    @Test
    public void testBuildResponseWithOnlyStatusLine() throws Exception {
        response.addStatusLine(status200);
        assertEquals(response200, response.getResponseHeader());
//        response.addBody(body);
//        assertEquals(response200 + "\r\n" + body, response.getResponseHeader());
    }

    @Test
    public void testBuildResponseWithStatusAndHeader() throws Exception {
        response.addStatusLine(status200);
        response.addHeader(htmlContentHeader);
        assertEquals(responseHeader200, response.getResponseHeader());
    }


}
