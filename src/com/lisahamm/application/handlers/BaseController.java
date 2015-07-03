package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.ResponseBuilder;

public abstract class BaseController {
    private String uri;

    public BaseController(String uri) {
        this.uri = uri;
    }

    public boolean itShouldExecute(Request request) {
        return uri.equals(request.getRequestURI());
    }

    public void execute(Request request, ResponseBuilder response) {
        switch (request.getRequestMethod()) {
            case "GET":
                handleGet(request, response);
                break;
            case "POST":
                handlePost(request, response);
                break;
            case "PUT":
                handlePut(request, response);
                break;
            case "PATCH":
                handlePatch(request, response);
                break;
            case "DELETE":
                handleDelete(request, response);
                break;
            case "OPTIONS":
                handleOptions(request, response);
                break;
            default:
                break;
        }
    }

    private void handleGet(Request request, ResponseBuilder response){};
    private void handlePost(Request request, ResponseBuilder response){};
    private void handlePut(Request request, ResponseBuilder response){};
    private void handlePatch(Request request, ResponseBuilder response){};
    private void handleDelete(Request request, ResponseBuilder response){};
    private void handleOptions(Request request, ResponseBuilder response){};
}
