package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private String httpVersion = "HTTP/1.1";
    private String CRLF = "\r\n";
    private String space = " ";
    private String response = "";
    private String statusLine;
    private String headers = "";
    private byte[] body;

    public void addStatusLine(String statusCode) {
        String status = responseStatuses().get(statusCode);
        statusLine = httpVersion + space + status + CRLF;
    }

    public void addHeader(String header) {
        headers += header + CRLF;
    }

    public void addBody(byte[] bodyContent) {
        body = bodyContent;
    }

    public String getResponseHeader() {
        constructResponseHeader();
        return response;
    }

    public byte[] getBody() {
        return body;
    }

    private void constructResponseHeader() {
        if (statusLine != null) {
            response = statusLine;
            if(headers != null) {
                response += headers;
            }
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
