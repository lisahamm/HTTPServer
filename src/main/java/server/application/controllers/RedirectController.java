package server.application.controllers;


import server.core.Constants.HttpStatus;
import server.core.requests.Request;
import server.core.managers.ResourceManager;
import server.core.response.ResponseBuilder;

public class RedirectController extends BaseController {

    public RedirectController(ResourceManager resourceManager) {
        this.uri = "/redirect";
        this.resourceManager = resourceManager;
    }

    @Override
    public void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE302.get());
        response.addHeader("Location: http://localhost:5000/");
    }
}
