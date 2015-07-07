package com.lisahamm.application.controllers;


import com.lisahamm.core.requests.Request;
import com.lisahamm.core.managers.ResourceManager;
import com.lisahamm.core.response.ResponseBuilder;

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
