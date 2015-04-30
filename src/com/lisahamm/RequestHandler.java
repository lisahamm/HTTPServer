package com.lisahamm;


public class RequestHandler {
    private String httpRequest;
    private String requestLine;

    public RequestHandler(String httpRequest) {
        this.httpRequest = httpRequest;
    }

    public void parseRequest() {
        requestLine = httpRequest;
    }

    public String getRequestLine() {
        return requestLine;
    }
}
