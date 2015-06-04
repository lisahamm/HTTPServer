package com.lisahamm.mocks;

import com.lisahamm.Request;

import java.util.Map;

public class MockHTTPRequest implements Request {
    public String requestMethod = null;
    public String requestURI = null;

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHTTPVersion() {
        return "HTTP/1.1";
    }

    public String getHeaders() {
        return null;
    }

    public String getBody() {
        return null;
    }

    public Map<String, String> getParams() {
        return null;
    }

}
