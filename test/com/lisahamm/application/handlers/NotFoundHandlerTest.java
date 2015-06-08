package com.lisahamm.application.handlers;

import com.lisahamm.ResponseBuilder;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NotFoundHandlerTest {
    private MockHTTPRequest mockRequest;
    private ResponseBuilder response;
    private NotFoundHandler notFoundHandler;
    @Before
    public void setUp() throws Exception {
        mockRequest = new MockHTTPRequest();
        response = new ResponseBuilder();
        notFoundHandler = new NotFoundHandler();
    }

    @Test
    public void testItAdds404StatusLineToResponse() throws Exception {
        mockRequest.requestMethod = "POST";
        mockRequest.requestURI = "/foo_bar";
        assertEquals("", response.getResponseHeader());
        boolean handled = notFoundHandler.handle(mockRequest, response);
        assertTrue(handled);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 404 Not Found"));
    }

}
