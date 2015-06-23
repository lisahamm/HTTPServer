package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.ResponseBuilder;

public class CookieHandler {
    public static final String uri = "/cookie";
    private static String code200 = "200";
    private static String code405 = "405";

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();

        if (request.getRequestURI().equals(uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    break;
                default:
                    response.addStatusLine(code405);
                    break;
            }
            return true;
        }
        return false;
    }
}
