package com.lisahamm;

public class MethodOptionsHandler implements RequestHandler {
    public static final String uri = "/method_options";

    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/plain");
                    response.addHeader("Allow: GET, HEAD, POST, OPTIONS, PUT");
                    break;
                case "POST":
                    response.addStatusLine("200");
                    response.addHeader("Allow: GET, HEAD, POST, OPTIONS, PUT");
                    break;
                case "PUT":
                    response.addStatusLine("200");
                    response.addHeader("Allow: GET, HEAD, POST, OPTIONS, PUT");
                    break;
                case "OPTIONS":
                    response.addStatusLine("200");
                    response.addHeader("Allow: GET,HEAD,POST,OPTIONS,PUT");
                    break;
                default:
                    response.addStatusLine("405");
                    response.addHeader("Allow: GET, HEAD, POST, OPTIONS, PUT");
                    break;
            }
            return true;
        }
        return false;
    }
}
