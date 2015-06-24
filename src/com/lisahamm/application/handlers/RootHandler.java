package com.lisahamm.application.handlers;


import com.lisahamm.*;
import com.lisahamm.application.HtmlBuilder;


import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RootHandler implements RequestHandler {
    private ResourceManager resourceManager;
    private HtmlBuilder viewBuilder;
    public static final String uri = "/";
    private static String code200 = "200";
    private static String code405 = "405";
    private static String contentTypeHTML = "Content-Type: text/html";

    public RootHandler(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.viewBuilder = new HtmlBuilder();
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
                    String view = addHtmlLinks(publicFiles);
                    response.addBody(view.getBytes());
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
        for (String file : fileNames) {
            viewBuilder.addLink("/" + file, file);
            viewBuilder.addNewLine();
        }
        return viewBuilder.getView();
    }
}
