package com.lisahamm.mocks;

import com.lisahamm.Request;

import java.util.HashMap;
import java.util.Map;

public class MockHTTPRequest implements Request {
    public String requestMethod;
    public String requestURI;
    public Map<String, String> params;
    public Map<String, String> headers;
    public String body;

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHTTPVersion() {
        return "HTTP/1.1";
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getParams() {
        return params;
    }

}
