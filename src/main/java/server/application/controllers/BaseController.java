package server.application.controllers;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.managers.ResourceManager;

public abstract class BaseController implements Controller {
    protected ResourceManager resourceManager;
    protected String uri;
    protected static String code200 = "200";
    protected static String code204 = "204";
    protected static String code206 = "206";
    protected static String code302 = "302";
    protected static String code401 = "401";
    protected static String code412 = "412";
    protected static String code405 = "405";
    protected static String contentTypeHTML = "Content-Type: text/html";
    protected static String contentTypeTextPlain = "Content-Type: text/plain";


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
            case "HEAD":
                handleHead(request, response);
            default:
                break;
        }
    }

    protected void handleGet(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handlePost(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handlePut(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handlePatch(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handleDelete(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handleOptions(Request request, ResponseBuilder response){
        response.addStatusLine(code405);
    }

    protected void handleHead(Request request, ResponseBuilder response) {
        response.addStatusLine(code405);
    }
}
