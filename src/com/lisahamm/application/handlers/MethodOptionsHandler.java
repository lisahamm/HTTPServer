package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

public class MethodOptionsHandler implements RequestHandler {
    public static final String uri = "/method_options";
    private static String code200 = "200";
    private static String code405 = "405";
    private static String allowHeader = "Allow: GET,HEAD,POST,OPTIONS,PUT";

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    response.addHeader("Content-Type: text/plain");
                    response.addHeader(allowHeader);
                    break;
                case "POST":
                    response.addStatusLine(code200);
                    response.addHeader(allowHeader);
                    break;
                case "PUT":
                    response.addStatusLine(code200);
                    response.addHeader(allowHeader);
                    break;
                case "OPTIONS":
                    response.addStatusLine(code200);
                    response.addHeader(allowHeader);
                    break;
                default:
                    response.addStatusLine(code405);
                    response.addHeader(allowHeader);
                    break;
            }
            return true;
        }
        return false;
    }
}
