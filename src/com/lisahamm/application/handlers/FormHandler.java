package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

public class FormHandler implements RequestHandler {
    public static final String uri = "/form";
    private static String code200 = "200";
    private static String code405 = "405";
    private String formData;

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    response.addHeader("Content-Type: text/plain");
                    if (getFormData() != null) {
                        response.addBody(getFormData().getBytes());
                    }
                    break;
                case "POST":
                    formData = request.getBody();
                    response.addStatusLine(code200);
                    break;
                case "PUT":
                    formData = request.getBody();
                    response.addStatusLine(code200);
                    break;
                case "DELETE":
                    formData = null;
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

    public String getFormData() {
        return this.formData;
    }
}
