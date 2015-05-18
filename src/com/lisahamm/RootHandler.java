package com.lisahamm;


import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RootHandler implements RequestHandler {
    private DirectoryManager directoryManager;
    public static final String uri = "/";

    public RootHandler(DirectoryManager directoryManager) {
        this.directoryManager = directoryManager;
    }

    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/html");
                    List<String> publicFiles = directoryManager.buildDirectoryContents();
                    String html = addHtmlLinks(publicFiles);
                    response.addBody(html.getBytes());
                   break;
                default:
                    response.addStatusLine("405");
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
