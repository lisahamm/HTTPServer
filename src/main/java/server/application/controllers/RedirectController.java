package server.application.controllers;


import server.core.requests.Request;
import server.core.managers.ResourceManager;
import server.core.response.ResponseBuilder;

public class RedirectController extends BaseController {

    public RedirectController(ResourceManager resourceManager) {
        this.uri = "/redirect";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(code302);
        response.addHeader("Location: http://localhost:5000/");
    }
}
