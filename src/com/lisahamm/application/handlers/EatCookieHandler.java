package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;
import com.lisahamm.application.HtmlBuilder;

public class EatCookieHandler implements RequestHandler {
    private HtmlBuilder viewBuilder;
    public static final String uri = "/eat_cookie";
    private String cookieKey = "type";
    private String imageFileName = "/cookie.png";
    private static String code200 = "200";
    private static String code405 = "405";

    public EatCookieHandler() {
        this.viewBuilder = new HtmlBuilder();
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();

        if (request.getRequestURI().equals(uri)) {
            switch(requestMethod) {
                case "GET":
                    String rawCookie = request.getHeaders().get("Cookie");
                    if (rawCookie != null) {
                        String cookieType = parseCookie(cookieKey, rawCookie);
                        String view = buildView(cookieType, imageFileName);
                        constructResponse(response, view);
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

    private void constructResponse(ResponseBuilder response, String view) {
        response.addStatusLine(code200);
        response.addHeader("Content-Type: text/html");
        response.addBody(view.getBytes());
    }

    private String parseCookie(String key, String cookie) {
        return cookie.split((key + "="), 2)[1];
    }

    private String buildView(String cookieType, String imageFileName) {
        viewBuilder.addDocType();
        viewBuilder.addOpeningHtmlTag();
        viewBuilder.addOpeningBodyTag();
        viewBuilder.addHeader("h1", "mmmm " + cookieType);
        viewBuilder.addImage(imageFileName);
        viewBuilder.addClosingBodyTag();
        viewBuilder.addClosingHtmlTag();
        return viewBuilder.getView();
    }
}