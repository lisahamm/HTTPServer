package com.lisahamm;

import java.util.*;

public class RequestRouter {
    private static List<RequestHandler> requestHandlers = new LinkedList<>();

    public void route(HTTPRequest request, ResponseBuilder response) {
        setup();
        System.out.println("Number of handlers: " + requestHandlers.size());
        boolean handled = false;
        while (handled == false) {
            for (RequestHandler requestHandler : requestHandlers) {
                handled = requestHandler.handle(request, response);
            }
        }
    }

    public static void registerHandler(RequestHandler handler) {
        requestHandlers.add(handler);
    }

    private static void setup() {
        registerHandler(new RootHandler());
    }
}
