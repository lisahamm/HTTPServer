package com.lisahamm;

public class FormHandler implements RequestHandler {
    public static final String uri = "/form";

    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/plain");
                    break;
                case "POST":
                    response.addStatusLine("200");
                    break;
                case "PUT":
                    response.addStatusLine("200");
                    break;
                default:
                    response.addStatusLine("405");
                    break;
            }
            return true;
        }
        return false;
    }
}
