package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class RequestParser implements Parser {
    private Request httpRequest;
    private Map<String,String> parsedRequestComponents = new HashMap<>();

    public RequestParser() {
    }

    public void parseRequest(Request httpRequest) {
        this.httpRequest = httpRequest;
        String request = httpRequest.getRawRequest().trim();
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
        updateWithParsedData();
    }

    public void updateWithParsedData() {
        httpRequest.setParsedDataFields(parsedRequestComponents);
    }
}
