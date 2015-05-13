//package com.lisahamm;
//
//public class OptionsRequestHandler implements RequestHandler {
//    private String response;
//
//    public void handle(HTTPRequest request) {
//        if (request.getRequestURI().equals("/method_options"))
//            response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\nContent-Length: 0\r\n";
//        else
//            response = "HTTP/1.1 404 Not Found";
//    }
//
//    public String getResponse() {
//        return response;
//    }
//}
