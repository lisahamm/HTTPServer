package com.lisahamm;

public class PutRequestHandler implements RequestHandler {
    private String response;

    public void handle(HTTPRequest request) {
        if (request.getRequestURI().equals("/form"))
            response = "HTTP/1.1 200 OK";
        else {
            response = "HTTP/1.1 404 Not Found";
        }
    }

    public String getResponse() {
        return response;
    }
}
