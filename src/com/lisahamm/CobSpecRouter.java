package com.lisahamm;

import com.lisahamm.application.handlers.*;

import java.util.LinkedList;
import java.util.List;

public class CobSpecRouter implements Router {
    private static String pathToPublicDirectory = "./../cob_spec/public";
    private static String pathToDataStorage = "./../cob_spec/database";
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
        FileManager fileManager = new AppFileManager();
        AppResourceManager resourceManager = new AppResourceManager(fileManager, pathToPublicDirectory, pathToDataStorage);
        registerHandler(new RootHandler(resourceManager));
        registerHandler(new FormHandler(resourceManager));
        registerHandler(new MethodOptionsHandler());
        registerHandler(new FileHandler(resourceManager));
        registerHandler(new RedirectHandler());
        registerHandler(new ParametersHandler());
        registerHandler(new LogHandler(resourceManager));
        registerHandler(new NotFoundHandler());
    }}
