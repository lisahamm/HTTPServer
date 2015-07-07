package com.lisahamm.core.response;

import com.lisahamm.core.response.ResponseBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseBuilderTest {
    private String status200 = "200";
    private String response200 = "HTTP/1.1 200 OK\r\n";
    private String htmlContentHeader = "Content-Type: text/html";
    private String allowHeader = "Allow: GET";
    private String responseWithHeader200 = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n";
    private String responseWithHeaders200 = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nAllow: GET\r\n";
    private byte[] body = "This is the body".getBytes();
    private ResponseBuilder response;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
    }

    @Test
    public void testBuildResponseWithOnlyStatusLine() throws Exception {
        response.addStatusLine(status200);
        assertEquals(response200, response.getResponseHeader());
    }

    @Test
    public void testBuildResponseWithStatusAndHeader() throws Exception {
        response.addStatusLine(status200);
        response.addHeader(htmlContentHeader);
        assertEquals(responseWithHeader200, response.getResponseHeader());
    }

    @Test
    public void testBuildResponseWithStatusAndMultipleHeaders() throws Exception {
        response.addStatusLine(status200);
        response.addHeader(htmlContentHeader);
        response.addHeader(allowHeader);
        assertEquals(responseWithHeaders200, response.getResponseHeader());
    }

    @Test
    public void testBuildResponseWithBody() throws Exception {
        response.addBody(body);
        assertEquals(body, response.getBody());
    }

    @Test
    public void testGetEntireResponse() throws Exception {
        response.addStatusLine("200");
        response.addHeader(htmlContentHeader);
        response.addBody(body);

        assertEquals(responseWithHeader200 + "\r\n" + new String(body), new String(response.getEntireResponse()));
    }
}
