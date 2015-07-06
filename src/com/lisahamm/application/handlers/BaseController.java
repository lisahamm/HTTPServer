package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.ResourceManager;
import com.lisahamm.ResponseBuilder;

public abstract class BaseController implements Controller {
    protected ResourceManager resourceManager;
    protected String uri;
    protected static String code200 = "200";
    protected static String code302 = "302";
    private static String code405 = "405";


    public boolean shouldExecute(Request request) {
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

    protected void handleGet(Request request, ResponseBuilder response){};
    protected void handlePost(Request request, ResponseBuilder response){};
    protected void handlePut(Request request, ResponseBuilder response){};
    protected void handlePatch(Request request, ResponseBuilder response){};
    protected void handleDelete(Request request, ResponseBuilder response){};
    protected void handleOptions(Request request, ResponseBuilder response){};
}
