package com.lisahamm.application.handlers;

import com.lisahamm.*;

import java.io.File;

public class FormHandler implements RequestHandler {
    public static final String uri = "/form";
    private static String code200 = "200";
    private static String code405 = "405";
    private ResourceManager resourceManager;

    public FormHandler(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    response.addHeader("Content-Type: text/plain");
                    String body = getFormData();
                    if (body.length() != 0) {
                        response.addBody(getFormData().getBytes());
                    }
                    break;
                case "POST":
                    updateResource(request.getBody());
                    response.addStatusLine(code200);
                    break;
                case "PUT":
                    updateResource(request.getBody());
                    response.addStatusLine(code200);
                    break;
                case "DELETE":
                    updateResource("");
                    response.addStatusLine(code200);
                    break;
                default:
                    response.addStatusLine(code405);
                    break;
            }
            return true;
        }
        return false;
    }

    private String getFormData() {
        return resourceManager.retrieveData(uri);
    }

    private void updateResource(String data) {
        resourceManager.updateResource(uri, data);
    }
}
