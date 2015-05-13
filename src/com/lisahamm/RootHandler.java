package com.lisahamm;


import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RootHandler implements RequestHandler {
    private String pathToPublicDirectory = "./../cob_spec/public";
    private Charset charset = StandardCharsets.UTF_8;

    public static final String uri = "/";

    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (requestURI.equals(this.uri)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/html; charset=UTF-8");
                    response.addBody(buildDirectoryContentsString());
                   break;
                default:
                    response.addStatusLine("405");
                    break;
            }
            return true;
        }
        return false;
    }

    private File[] buildDirectoryContents() {
        File f = new File(pathToPublicDirectory);
        File[] files = f.listFiles();
        return files;
    }

    private String buildDirectoryContentsString() {
        String directoryContents = "<p>";
        File[] directoryFiles = buildDirectoryContents();
        for(File file : directoryFiles) {
            String fileName = file.getName();
            String filePath = "/" + fileName;
            directoryContents += "<a href=\"" + filePath + "\">" + fileName + "</a>" + "\r\n";

        }
        directoryContents += "</body>";
        return directoryContents;
    }

}
