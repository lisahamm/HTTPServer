package com.lisahamm.application.controllers;

import com.lisahamm.core.requests.Request;
import com.lisahamm.core.managers.ResourceManager;
import com.lisahamm.core.response.ResponseBuilder;

public class MethodOptionsController extends BaseController {
    private static String allowHeader = "Allow: GET,HEAD,POST,OPTIONS,PUT";

    public MethodOptionsController(ResourceManager resourceManager) {
        this.uri = "/method_options";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(allowHeader);
    }

    @Override
    protected void handlePost(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(allowHeader);
    }

    @Override
    protected void handlePut(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(allowHeader);
    }

    @Override
    protected void handleOptions(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(allowHeader);
    }

    @Override
    protected void handleHead(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(allowHeader);
    }
}
