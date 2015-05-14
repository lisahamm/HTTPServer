package com.lisahamm;

import java.util.*;

public class RequestRouter {
    private List<RequestHandler> requestHandlers = new LinkedList<>();

    public void route(HTTPRequest request, ResponseBuilder response) {
        setup();
        System.out.println("Number of handlers: " + requestHandlers.size());
        boolean handled = false;
        for (RequestHandler requestHandler : requestHandlers) {
            if (handled) {
                break;
            }
            handled = requestHandler.handle(request, response);
        }
    }

    public void registerHandler(RequestHandler handler) {
        requestHandlers.add(handler);
    }

    private void setup() {
        registerHandler(new RootHandler());
        registerHandler(new FormHandler());
        registerHandler(new MethodOptionsHandler());
        registerHandler(new FileHandler());
        registerHandler(new RedirectHandler());
    }
}
