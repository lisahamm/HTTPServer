package com.lisahamm.application.handlers;

import com.lisahamm.FileManager;
import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileHandler implements RequestHandler {
    private FileManager fileManager;

    public FileHandler(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String fileName = request.getRequestURI().substring(1);

        if (fileManager.isFileFound(fileName)) {
            switch(requestMethod) {
                case "GET":
                    processGetRequest(request, response);
                    break;
                case "PATCH":
                   processPatchRequest(request, response);
                    break;
                default:
                    processRequestWithMethodNotAllowed(response);
                    break;
            }
            return true;
        }
        return false;
    }

    private void processGetRequest(Request request, ResponseBuilder response) {
        byte[] body;
        if (request.getHeaders().containsKey("Range")) {
            response.addStatusLine("206");
            body = getPartialContents(request, fileManager);
        } else {
            response.addStatusLine("200");
            body = fileManager.getFileContents(request.getRequestURI());
        }

        String mimeType = fileManager.getContentType(request.getRequestURI());
        response.addHeader("Content-Type: " + mimeType);
        response.addHeader("Content-Length: " + body.length);
        response.addBody(body);
    }

    private void processPatchRequest(Request request, ResponseBuilder response) {
        String requestEtag = request.getHeaders().get("If-Match");
        if (isPatchPreconditionMet(request.getRequestURI(), requestEtag)) {
            fileManager.overwriteFile(request.getRequestURI(), request.getBody());
            response.addStatusLine("204");
            addEtagToResponse(request.getRequestURI(), response);
        } else {
            response.addStatusLine("412");
        }
    }

    private void processRequestWithMethodNotAllowed(ResponseBuilder response) {
        response.addStatusLine("405");
    }

    private String parseRange(Request request) {
        return request.getHeaders().get("Range").split("=") [1];
    }

    private byte[] getPartialContents(Request request, FileManager fileManager) {
        String range = parseRange(request);
        return fileManager.getPartialFileContents(request.getRequestURI(), range);
    }

    private String sha1Encoding(byte[] byteArray) {
        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-1");
            String sha1Encoded = DatatypeConverter.printHexBinary(encoder.digest(byteArray));
            return sha1Encoded.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isPatchPreconditionMet(String requestURI, String requestEtag) {
        byte[] currentFileContents = fileManager.getFileContents(requestURI);
        return requestEtag.equals(sha1Encoding(currentFileContents));
    }

    private void addEtagToResponse(String requestURI, ResponseBuilder response) {
        String responseEtag = sha1Encoding(fileManager.getFileContents(requestURI));
        response.addHeader("ETag: " + responseEtag);
    }

}
