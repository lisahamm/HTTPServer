package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private String httpVersion = "HTTP/1.1";
    private String CRLF = "\r\n";
    private String response = "";
    private String statusLine;
    private String headers = "";
    private String body = "";

    public void addStatusLine(String statusCode) {
        String status = responseStatuses().get(statusCode);
        response += httpVersion + " " + status + CRLF;
    }

    public void addHeader(String header) {
        response += header + CRLF;
    }

    public void addBody(String bodyContent) {
        response += CRLF + bodyContent;
    }

    public String getResponse() {
        return response;
    }

    private void constructResponse() {
        response = statusLine;
        if(headers.length() > 1) {
            response += headers;
        }
        if(body.length() > 1) {
            response += body;
        }
    }

    private Map<String, String> responseStatuses() {
        Map<String, String> statuses = new HashMap<>();
        statuses.put("200", "200 OK");
        statuses.put("302", "302 Found");
        statuses.put("404", "404 Not Found");
        statuses.put("405", "405 Method Not Allowed");
        return statuses;
    }
}
