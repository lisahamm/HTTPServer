package com.lisahamm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler implements RequestHandler {
    private String pathToPublicDirectory = "./../cob_spec/public";
    private Charset charset = StandardCharsets.UTF_8;


    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (fileExistsInDirectory(requestURI)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/html");
                    response.addBody(buildBodyContentFromURI(requestURI, charset));
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

    private String buildBodyContentFromURI(String requestURI, Charset encoding) {
        String uriPath = pathToPublicDirectory + requestURI;
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(uriPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }

    private boolean fileExistsInDirectory(String requestURI) {
        boolean exists = false;

        File[] directoryFiles = buildDirectoryContents();

        for(File file : directoryFiles) {
            if (("/" + file.getName()).equals(requestURI)) {
                exists = true;
            }
        }
        return exists;
    }
}
