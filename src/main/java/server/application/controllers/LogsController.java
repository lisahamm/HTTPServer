package server.application.controllers;

import server.application.Resources;
import server.core.Constants.HttpHeader;
import server.core.Constants.HttpStatus;
import server.core.helpers.BasicAuth;
import server.core.requests.Request;
import server.core.managers.ResourceManager;
import server.core.response.ResponseBuilder;

public class LogsController extends BaseController {
    private static String validUserID = "admin";
    private static String validPassword = "hunter2";

    public LogsController(ResourceManager resourceManager) {
        this.uri = "/logs";
        this.resourceManager = resourceManager;
    }

    @Override
    public void handleGet(Request request, ResponseBuilder response) {
        if (isAuthorized(request)) {
            response.addStatusLine(HttpStatus.CODE200.get());
            String body = getLogs();
            response.addBody(body.getBytes());
        } else {
            response.addStatusLine(HttpStatus.CODE401.get());
            response.addHeader(HttpHeader.CONTENT_TYPE_PLAIN.get());
            response.addBody("Authentication required".getBytes());
        }
    }

    private boolean isAuthorized(Request request) {
        return BasicAuth.isAuthorized(request, validUserID, validPassword);
    }

    private String getLogs() {
        return Resources.logs.toString();
    }
}
