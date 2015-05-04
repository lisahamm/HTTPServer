package com.lisahamm;

public class RequestParser implements Parser {
    private String httpRequest;
    private String requestLine;
    private String headers;
    private String body;

    public RequestParser(String httpRequest) {
        this.httpRequest = httpRequest;
    }

    public void parseRequest() {
        String request = httpRequest;
        String[] messageComponents = httpRequest.split("\r\n\r\n");
        if (messageComponents.length > 1) {
            body = messageComponents[1];
            request = messageComponents[0];
        }
        messageComponents = request.split("\r\n", 2);
        requestLine = messageComponents[0];
        if (messageComponents.length > 1) {
            headers = messageComponents[1];
        }
    }

    public String getRequestLine() {
        return requestLine;
    }

    public String getHeaders(){
        return headers;
    }

    public String getBody() {
        return body;
    }

}
