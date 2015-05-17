package com.lisahamm;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler implements RequestHandler {
    private String pathToPublicDirectory = "./../cob_spec/public";


    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();

        if (fileExistsInDirectory(requestURI)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    String mimeType = getContentType(requestURI);
                    response.addHeader("Content-Type: " + mimeType);
                    response.addBody(buildBodyContentFromURI(requestURI));
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

    private byte[] buildBodyContentFromURI(String requestURI) {
        String uriPath = pathToPublicDirectory + requestURI;
        byte[] data = "".getBytes();
        try {
            data = Files.readAllBytes(Paths.get(uriPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private String getContentType(String requestURI) {
        File file = new File(pathToPublicDirectory + requestURI);
        String mimeType = "";
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            mimeType = URLConnection.guessContentTypeFromStream(is);
            if (mimeType == null) {
                mimeType = "text/plain";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mimeType;
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
