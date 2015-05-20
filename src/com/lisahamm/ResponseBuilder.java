package com.lisahamm;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private String httpVersion = "HTTP/1.1";
    private String CRLF = "\r\n";
    private String space = " ";
    private StringBuilder responseHeader = new StringBuilder();
    private String statusLine;
    private StringBuilder headers = new StringBuilder();
    private byte[] body;

    public void addStatusLine(String statusCode) {
        String status = responseStatuses().get(statusCode);
        statusLine = httpVersion + space + status + CRLF;
    }

    public void addHeader(String header) {
        headers.append(header + CRLF);
    }

    public void addBody(byte[] bodyContent) {
        body = bodyContent;
    }

    public String getResponseHeader() {
        constructResponseHeader();
        return responseHeader.toString();
    }

    public byte[] getBody() {
        return body;
    }

    private void constructResponseHeader() {
        if (statusLine != null) {
            responseHeader.append(statusLine);
            if(headers.length() > 0) {
                responseHeader.append(headers);
            }
        }
    }

    private Map<String, String> responseStatuses() {
        Map<String, String> statuses = new HashMap<>();
        statuses.put("200", "200 OK");
        statuses.put("206", "206 Partial Content");
        statuses.put("302", "302 Found");
        statuses.put("404", "404 Not Found");
        statuses.put("405", "405 Method Not Allowed");
        return statuses;
    }
}
