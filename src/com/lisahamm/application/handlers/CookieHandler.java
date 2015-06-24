package com.lisahamm.application.handlers;

import com.lisahamm.*;
import com.lisahamm.application.HtmlBuilder;

public class CookieHandler implements RequestHandler {
    public static final String uri = "/cookie";
    private HtmlBuilder viewBuilder;
    private static String cookieParamKey = "type";
    private static String code200 = "200";
    private static String code405 = "405";

    public CookieHandler() {
        viewBuilder = new HtmlBuilder();
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();

        if (request.getRequestURI().equals(uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    String cookieType = request.getParams().get(cookieParamKey);

                    if (cookieType != null) {
                        String cookie = cookieParamKey + "=" + cookieType;
                        response.addHeader("Set-Cookie: " + cookie);
                        response.addHeader("Content-Type: text/html");

                        String view = buildView("/eat_cookie", "Eat");

                        response.addBody(view.getBytes());
                    }
                    break;
                default:
                    response.addStatusLine(code405);
                    break;
            }
            return true;
        }
        return false;
    }

    private String buildView(String uri, String displayText) {
        viewBuilder.addDocType();
        viewBuilder.addOpeningHtmlTag();
        viewBuilder.addOpeningBodyTag();
        viewBuilder.addLink(uri, displayText);
        viewBuilder.addClosingBodyTag();
        viewBuilder.addClosingHtmlTag();
        return viewBuilder.getView();
    }
}
