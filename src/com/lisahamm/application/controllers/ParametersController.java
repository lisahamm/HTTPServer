package com.lisahamm.application.controllers;

import com.lisahamm.core.requests.Request;
import com.lisahamm.core.managers.ResourceManager;
import com.lisahamm.core.response.ResponseBuilder;

import java.util.Map;

public class ParametersController extends BaseController {
    public ParametersController(ResourceManager resourceManager) {
        this.uri = "/parameters";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(code200);
        response.addHeader(contentTypeTextPlain);
        String requestParams = paramsHashToString(request.getParams());
        response.addBody(requestParams.getBytes());
    }

    private String paramsHashToString(Map<String, String> paramsHash) {
        String paramString = "";
        for (Map.Entry<String, String> entry : paramsHash.entrySet())
        {
            paramString += entry.getKey() + " = " + entry.getValue() + "\r\n";
        }
        return paramString;
    }
}
