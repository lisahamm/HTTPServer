package server.application.controllers;

import server.application.Resources;
import server.core.requests.Request;
import server.core.managers.ResourceManager;
import server.core.response.ResponseBuilder;

import java.util.Base64;

public class LogsController extends BaseController {
    private static String validUserID = "admin";
    private static String validPassword = "hunter2";

    public LogsController(ResourceManager resourceManager) {
        this.uri = "/logs";
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        if (isAuthorized(request)) {
            response.addStatusLine("200");
            String body = getLogs();
            response.addBody(body.getBytes());
        } else {
            response.addStatusLine(code401);
            response.addHeader(contentTypeTextPlain);
            response.addBody("Authentication required".getBytes());
        }
    }

    private boolean isAuthorized(Request request) {
        if (request.getHeaders().containsKey("Authorization")) {
            String encodedCredentials = parseAuthorizationHeader(request);
            String decodedCredentials = base64Decode(encodedCredentials.trim());

            String userID = parseLoginCredentials(decodedCredentials)[0];
            String password = parseLoginCredentials(decodedCredentials)[1];

            return userID.equals(validUserID) && password.equals(validPassword);
        }
        return false;
    }

    private String parseAuthorizationHeader(Request request) {
        String credentials = request.getHeaders().get("Authorization");
        return credentials.split("Basic", 2)[1];
    }

    private String base64Decode(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return new String(decoded);
    }

    private String[] parseLoginCredentials(String decodedCredentials) {
        return decodedCredentials.split(":", 2);
    }

    private String getLogs() {
        return Resources.logs.toString();
    }
}
