package com.lisahamm;

import java.util.Map;

public class HTTPRequest implements Request {
    public String rawRequest;
    private Parser requestParser;
    private String requestMethod;
    private String requestURI;
    private String httpVersion;
    private String headers;
    private String body;


    public HTTPRequest(String rawRequest, Parser requestParser) {
        this.rawRequest = rawRequest;
        this.requestParser = requestParser;
    }

    public void parse() {
        requestParser.parseRequest(this);
    }

    public void setParsedDataFields(Map<String, String> parsedRequestComponents) {
        requestMethod = parsedRequestComponents.get("requestMethod");
        requestURI = parsedRequestComponents.get("requestURI");
        httpVersion = parsedRequestComponents.get("httpVersion");
        headers = parsedRequestComponents.get("headers");
        body = parsedRequestComponents.get("body");
    }

    public String getRawRequest() {
        return rawRequest;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHTTPVersion() {
        return httpVersion;
    }

    public String getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }
}
