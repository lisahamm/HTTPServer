package server.application.controllers;

import server.application.Resources;
import server.core.Constants.HttpHeader;
import server.core.Constants.HttpStatus;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.managers.ResourceManager;

public class FormController extends BaseController {

    public FormController(ResourceManager resourceManager) {
        this.uri = "/form";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(HttpHeader.CONTENT_TYPE_HTML.get());
        String body = getFormData();
        if (body.length() != 0) {
            response.addBody(body.getBytes());
        }
    }

    @Override
    protected void handlePost(Request request, ResponseBuilder response) {
        updateResource(request.getBody());
        response.addStatusLine(HttpStatus.CODE200.get());
    }

    @Override
    protected void handlePut(Request request, ResponseBuilder response) {
        updateResource(request.getBody());
        response.addStatusLine(HttpStatus.CODE200.get());
    }

    @Override
    protected void handleDelete(Request request, ResponseBuilder response) {
        updateResource("");
        response.addStatusLine(HttpStatus.CODE200.get());
    }

    private String getFormData() {
        return Resources.form;
    }

    private void updateResource(String data) {
        Resources.form = data;
    }
}
