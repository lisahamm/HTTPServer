package com.lisahamm;

public class GetRequestHandler implements RequestHandler {

    private String response;

    public void handle(HTTPRequest request) {
        if (request.getRequestURI().equals("/redirect")) {
            response = "HTTP/1.1 302 Found\r\nLocation: http://localhost:5000/\r\n";
        } else if (request.getRequestURI().equals("/")) {
            response = "HTTP/1.1 200 OK";
        } else {
            response = "HTTP/1.1 404 Not Found";
        }
    }

    public String getResponse() {
        return response;
    }
}
