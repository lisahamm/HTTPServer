package server.application.controllers;

import server.core.Constants.HttpHeader;
import server.core.Constants.HttpStatus;
import server.core.requests.Request;
import server.core.managers.ResourceManager;
import server.core.response.ResponseBuilder;

import java.util.Map;

public class ParametersController extends BaseController {
    public ParametersController(ResourceManager resourceManager) {
        this.uri = "/parameters";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(HttpStatus.CODE200.get());
        response.addHeader(HttpHeader.CONTENT_TYPE_PLAIN.get());
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
