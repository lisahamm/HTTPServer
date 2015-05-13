//package com.lisahamm;
//
//public class GetRequestHandler implements RequestHandler {
//    private String response;
//    private ResponseBuilder builder;
//
//    public GetRequestHandler(ResponseBuilder builder) {
//        this.builder = builder;
//    }
//
//    public void handle(HTTPRequest request) {
//        builder.constructStatusLine(request);
//        builder.constructHeaders(request);
//        builder.constructBody(request);
//        response = builder.getResponse();
//    }
//
//    public String getResponse() {
//        return response;
//    }
//}
