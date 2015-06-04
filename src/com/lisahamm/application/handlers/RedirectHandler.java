package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RedirectHandler implements RequestHandler {
    public static final String uri = "/redirect";

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("302");
                    response.addHeader("Location: http://localhost:5000/");
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
