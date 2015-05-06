package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private String rawRequest;
    private Map<String,String> parsedRequestComponents = new HashMap<>();

    public RequestParser() {
    }

    public HTTPRequest generateParsedRequest(String rawRequest) {
        parseRequest(rawRequest);
        return new HTTPRequest(parsedRequestComponents);
    }

    private void parseRequest(String rawRequest) {
        String request = rawRequest.trim();
        String[] messageComponents = request.split("\r\n\r\n");
        if (messageComponents.length > 1) {
            parsedRequestComponents.put("body", messageComponents[1]);
            request = messageComponents[0];
        }
        messageComponents = request.split("\r\n", 2);
        String requestLine = messageComponents[0];
        parsedRequestComponents.put("requestMethod", requestLine.split(" ")[0]);
        parsedRequestComponents.put("requestURI", requestLine.split(" ")[1]);
        parsedRequestComponents.put("httpVersion", requestLine.split(" ")[2]);

        if (messageComponents.length > 1) {
            parsedRequestComponents.put("headers", messageComponents[1]);
        }
    }
}
