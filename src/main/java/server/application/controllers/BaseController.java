package server.application.controllers;

import server.core.Constants.HttpStatus;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.managers.ResourceManager;

public abstract class BaseController implements Controller {
    protected ResourceManager resourceManager;
    protected String uri;

    public void handleGet(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handlePost(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handlePut(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handlePatch(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handleDelete(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handleOptions(Request request, ResponseBuilder response){
        response.addStatusLine(HttpStatus.CODE405.get());
    }

    public void handleHead(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE405.get());
    }
}
