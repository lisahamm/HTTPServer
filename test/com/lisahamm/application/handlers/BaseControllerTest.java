package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseControllerTest {
    @Test
    public void testItShouldExecuteWhenRequestHasMatchingURI() throws Exception {
        String uri = "/form";
        BaseController controller = new BaseController(uri);
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestURI = uri;

        assertTrue(controller.itShouldExecute(request));
    }

    @Test
    public void testItShouldNotExecuteIfRequestUriIsNotAMatch() throws Exception {
        String uri = "/form";
        BaseController controller = new BaseController(uri);
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestURI = "/form2";

        assertFalse(controller.itShouldExecute(request));
    }
}