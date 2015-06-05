package com.lisahamm.application.handlers;

import com.lisahamm.FileManager;
import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

import java.io.File;
import java.util.Arrays;

public class FileHandler implements RequestHandler {
    private FileManager fileManager;

    public FileHandler(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();
        String fileName = requestURI.substring(1);

        if (fileManager.isFileFound(fileName)) {

            switch(requestMethod) {
                case "GET":
                    byte[] body;
                    if (request.getHeaders().containsKey("Range")) {
                        response.addStatusLine("206");
                        body = getPartialContents(request, fileManager);
                    } else {
                        response.addStatusLine("200");
                        body = fileManager.getFileContents(requestURI);
                    }
                    int bodyLength = body.length;
                    response.addHeader("Content-Length: " + bodyLength);
                    String mimeType = fileManager.getContentType(requestURI);
                    response.addHeader("Content-Type: " + mimeType);
                    response.addBody(body);
                    break;
                default:
                    response.addStatusLine("405");
                    break;
            }
            return true;
        }
        return false;
    }

    private String parseRange(Request request) {
        return request.getHeaders().get("Range").split("=") [1];
    }

    private byte[] getPartialContents(Request request, FileManager fileManager) {
        String range = parseRange(request);
        return fileManager.getPartialFileContents(request.getRequestURI(), range);
    }
}
