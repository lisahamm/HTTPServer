package com.lisahamm;

import com.lisahamm.application.handlers.*;

import java.util.LinkedList;
import java.util.List;

public class CobSpecRouter implements Router {
    private List<RequestHandler> requestHandlers = new LinkedList<>();

    public CobSpecRouter() {
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
        String pathToPublicDirectory = "./../cob_spec/public";
        registerHandler(new RootHandler(new PublicFileManager(pathToPublicDirectory)));
        registerHandler(new FormHandler());
        registerHandler(new MethodOptionsHandler());
        registerHandler(new FileHandler(new PublicFileManager(pathToPublicDirectory)));
        registerHandler(new RedirectHandler());
        registerHandler(new ParametersHandler());
    }}
