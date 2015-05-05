package com.lisahamm;

import org.junit.Test;

public class RequestHandlerFactoryTest {
    @Test
    public void testCreateGetRequestHandler() throws Exception {
        RequestHandlerFactory factory = new RequestHandlerFactory();
        RequestHandler handler = factory.make("GET");
        assert(handler instanceof GetRequestHandler);
    }
}
