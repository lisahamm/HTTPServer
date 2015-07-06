package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.ResourceManager;
import com.lisahamm.ResponseBuilder;

public class FormController extends BaseController {

    public FormController(ResourceManager resourceManager) {
        this.uri = "/form";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader("Content-Type: text/plain");
        String body = getFormData();
        if (body.length() != 0) {
            response.addBody(body.getBytes());
        }
    }

    @Override
    protected void handlePost(Request request, ResponseBuilder response) {
        updateResource(request.getBody());
        response.addStatusLine(code200);
    }

    @Override
    protected void handlePut(Request request, ResponseBuilder response) {
        updateResource(request.getBody());
        response.addStatusLine(code200);
    }

    @Override
    protected void handleDelete(Request request, ResponseBuilder response) {
        updateResource(request.getBody());
        response.addStatusLine(code200);
    }

    private String getFormData() {
        return resourceManager.retrieveData(uri);
    }

    private void updateResource(String data) {
        resourceManager.updateResource(uri, data);
    }
}
