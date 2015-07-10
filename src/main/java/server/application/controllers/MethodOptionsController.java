package server.application.controllers;

import server.core.Constants.HttpStatus;
import server.core.managers.ResourceManager;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;

public class MethodOptionsController extends BaseController {
    private static String allowHeader = "Allow: GET,HEAD,POST,OPTIONS,PUT";

    public MethodOptionsController(ResourceManager resourceManager) {
        this.uri = "/method_options";
        this.resourceManager = resourceManager;
    }

    @Override
    public void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(allowHeader);
    }

    @Override
    public void handlePost(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(allowHeader);
    }

    @Override
    public void handlePut(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(allowHeader);
    }

    @Override
    public void handleOptions(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(allowHeader);
    }

    @Override
    public void handleHead(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(allowHeader);
    }
}
