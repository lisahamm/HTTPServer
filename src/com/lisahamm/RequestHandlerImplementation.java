package com.lisahamm;

public class RequestHandlerImplementation {
    private HTTPRequest request;
    private String responseLine;

    public RequestHandlerImplementation(HTTPRequest request) {
        this.request = request;
    }

    public void handle() {
        if (request.getRequestMethod().contains("GET")) {
            if (request.getRequestURI().contains("/redirect")) {
                responseLine = "HTTP/1.1 302 Found\r\nLocation: http://localhost:5000/\r\n";
            } else if (request.getRequestURI().equals("/")) {
                responseLine = "HTTP/1.1 200 OK";
            } else {
                responseLine = "HTTP/1.1 404 Not Found";
            }
        } else if (request.getRequestMethod().contains("POST")) {
            responseLine = "HTTP/1.1 200 OK";
        } else if (request.getRequestMethod().contains("PUT")) {
            responseLine = "HTTP/1.1 200 OK";
        } else if (request.getRequestMethod().contains("OPTIONS")) {
            responseLine = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\nContent-Length: 0\r\n";
        } else {
            responseLine = "HTTP/1.1 404 Not Found";
        }
    }

    public String getResponseLine() {
        return responseLine;
    }
//    public String constructResponse() {
//        String CRLF = "\r\n";
//        String responseString = "";
//        responseString += getResponseLine() + CRLF;
//        responseString += getHeaders() + CRLF;
//        responseString += getBody();
//        return responseString;
//    }
//
//    public String getResponseLine() {
//        return "HTTP/1.1 200 OK\r\n";
//    }
//
//    public String getHeaders() {
//        return "";
//    }
//
//    public String getBody() {
//        return "";
//    }
}
