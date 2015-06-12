package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResourceManager;
import com.lisahamm.ResponseBuilder;

import java.util.Base64;

public class LogHandler implements RequestHandler {
    private static final String uri = "/logs";
    private static String validUserID = "admin";
    private static String validPassword = "hunter2";
    private ResourceManager resourceManager;

    public LogHandler(ResourceManager resourceManager){
        this.resourceManager = resourceManager;
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch (requestMethod) {
                case "GET":
                    if(authorized(request)) {
                        response.addStatusLine("200");
                        String body = getLogs();
                        response.addBody(body.getBytes());
                    } else {
                        response.addStatusLine("401");
                        response.addBody("Authentication required".getBytes());
                    }
                    break;
                default:
                    response.addStatusLine("405");
                    break;
            }
            return true;
        }
        return false;
    }

    private boolean authorized(Request request) {
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
        return resourceManager.retrieveData(uri);
    }
}
