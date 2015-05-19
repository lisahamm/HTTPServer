package com.lisahamm;

import java.util.Map;

public class HTTPRequest {
    private String requestMethod;
    private String requestURI;
    private String httpVersion;
    private String headers;
    private String body;
    private Map<String, String> params;

    public HTTPRequest(Map<String, String> parsedRequestComponents, Map<String,String> params) {
        requestMethod = parsedRequestComponents.get("requestMethod");
        requestURI = parsedRequestComponents.get("requestURI");
        httpVersion = parsedRequestComponents.get("httpVersion");
        headers = parsedRequestComponents.get("headers");
        body = parsedRequestComponents.get("body");
        this.params = params;
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

    public Map<String, String> getParams() {
        return params;
    }
}
