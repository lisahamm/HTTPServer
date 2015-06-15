package com.lisahamm;

import com.lisahamm.application.handlers.*;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class CobSpecRouter implements Router {
    private static ResourceManager resourceManager;
    private List<RequestHandler> requestHandlers = new LinkedList<>();

    public CobSpecRouter(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        registerHandlers();
    }

    public void invoke(HTTPRequest request, ResponseBuilder response) {
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

    private void registerHandlers() {
        registerHandler(new RootHandler(resourceManager));
        registerHandler(new FormHandler(resourceManager));
        registerHandler(new MethodOptionsHandler());
        registerHandler(new FileHandler(resourceManager));
        registerHandler(new RedirectHandler());
        registerHandler(new ParametersHandler());
        registerHandler(new LogHandler(resourceManager));
        registerHandler(new NotFoundHandler());
    }}
