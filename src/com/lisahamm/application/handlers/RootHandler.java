package com.lisahamm.application.handlers;


import com.lisahamm.*;
import sun.net.ResourceManager;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RootHandler implements RequestHandler {
    private AppResourceManager resourceManager;
    public static final String uri = "/";
    private static String code200 = "200";
    private static String code405 = "405";
    private static String contentTypeHTML = "Content-Type: text/html";


    public RootHandler(AppResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine(code200);
                    response.addHeader(contentTypeHTML);
                    List<String> publicFiles = resourceManager.getPublicDirectoryContents();
                    String html = addHtmlLinks(publicFiles);
                    response.addBody(html.getBytes());
                   break;
                default:
                    response.addStatusLine(code405);
                    break;
            }
            return true;
        }
        return false;
    }

    private String addHtmlLinks(List<String>fileNames) {
        String html = "";
        html += "<p>";
        for(String file : fileNames) {
            String filePath = "/" + file;
            html += addHtmlLink(filePath, file);
            html += "<br>";
        }
        html += "</p>";
        return html;
    }

    private String addHtmlLink(String link, String linkName) {
        String html = "<a href=\"";
        html += link + "\">" + linkName + "</a>";
        return html;
    }
}
