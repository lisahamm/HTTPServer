package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private Map<String,String> parsedRequestComponents = new HashMap<>();

    public RequestParser() {
    }

    public HTTPRequest generateParsedRequest(String rawRequest) {
        parseRequest(rawRequest);
        return new HTTPRequest(parsedRequestComponents);
    }

    private void parseRequest(String rawRequest) {
        String request = rawRequest.trim();
        String[] messageComponents = request.split(" ", 3);
        parsedRequestComponents.put("requestMethod", messageComponents[0]);
        parsedRequestComponents.put("requestURI", messageComponents[1]);
        messageComponents = messageComponents[2].split("\r\n", 2);
        parsedRequestComponents.put("httpVersion", messageComponents[0]);
        if (messageComponents.length > 1) {
            messageComponents = messageComponents[1].split("\r\n\r\n");
            parsedRequestComponents.put("headers", messageComponents[0]);
            if (messageComponents.length > 1) {
                parsedRequestComponents.put("body", messageComponents[messageComponents.length - 1]);
            }
        }
    }
}
