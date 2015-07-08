package server.application.controllers;

import server.core.Constants.Response;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.managers.ResourceManager;

public abstract class BaseController implements Controller {
    protected ResourceManager resourceManager;
    protected String uri;

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
        response.addStatusLine(Response.code405);
    }

    protected void handlePost(Request request, ResponseBuilder response){
        response.addStatusLine(Response.code405);
    }

    protected void handlePut(Request request, ResponseBuilder response){
        response.addStatusLine(Response.code405);
    }

    protected void handlePatch(Request request, ResponseBuilder response){
        response.addStatusLine(Response.code405);
    }

    protected void handleDelete(Request request, ResponseBuilder response){
        response.addStatusLine(Response.code405);
    }

    protected void handleOptions(Request request, ResponseBuilder response){
        response.addStatusLine(Response.code405);
    }

    protected void handleHead(Request request, ResponseBuilder response) {
        response.addStatusLine(Response.code405);
    }


}
