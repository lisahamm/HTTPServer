package com.lisahamm;

import java.util.*;

public class RequestRouter {
    private List<RequestHandler> requestHandlers = new LinkedList<>();

    public void route(HTTPRequest request, ResponseBuilder response) {
        setup();
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
        String pathToPublicDirectory = "./../cob_spec/public";
        registerHandler(new RootHandler(new PublicFileManager(pathToPublicDirectory)));
        registerHandler(new FormHandler());
        registerHandler(new MethodOptionsHandler());
        registerHandler(new FileHandler(new PublicFileManager(pathToPublicDirectory)));
        registerHandler(new RedirectHandler());
        registerHandler(new ParametersHandler());
    }
}
