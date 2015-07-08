package server.application.controllers;

import server.core.Constants.Response;
import server.core.managers.ResourceManager;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class StaticFileController extends BaseController {

    public StaticFileController(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        byte[] body;
        if (request.getHeaders().containsKey("Range")) {
            response.addStatusLine(Response.code206);
            body = getPartialContents(request);
        } else {
            response.addStatusLine(Response.code200);
            body = resourceManager.getFileContents(request.getRequestURI());
        }

        String mimeType = resourceManager.getContentType(request.getRequestURI());
        response.addHeader("Content-Type: " + mimeType);
        response.addHeader("Content-Length: " + body.length);
        response.addBody(body);
    }

    @Override
    protected void handlePatch(Request request, ResponseBuilder response) {
        String requestEtag = request.getHeaders().get("If-Match");
        if (isPatchPreconditionMet(request.getRequestURI(), requestEtag)) {
            resourceManager.patchResource(request.getRequestURI(), request.getBody());
            response.addStatusLine(Response.code204);
            addEtagToResponse(request.getRequestURI(), response);
        } else {
            response.addStatusLine(Response.code412);
        }
    }

    private String parseRangeFromHeader(Request request) {
        return request.getHeaders().get("Range").split("=") [1];
    }

    private Map<String, Integer> parseRangeBoundaries(String range, int fileLength) {
        Map<String, Integer> rangeBoundaries = new HashMap<>();

        String[] rangeValues = range.split("-");
        int startIndex;
        int endIndex;

        if (range.lastIndexOf("-") == range.length()-1) {
            startIndex = Integer.parseInt(rangeValues[0]);
            endIndex = fileLength - 1;
        } else if (range.lastIndexOf("-") == 0) {
            startIndex = (fileLength) - Integer.parseInt(rangeValues[1]);
            endIndex = fileLength - 1;
        } else {
            startIndex = Integer.parseInt(rangeValues[0]);
            endIndex = Integer.parseInt(rangeValues[1]);
        }

        rangeBoundaries.put("startIndex", startIndex);
        rangeBoundaries.put("endIndex", endIndex);

        return rangeBoundaries;
    }

    private byte[] getPartialContents(Request request) {
        String range = parseRangeFromHeader(request);
        int fileLength = resourceManager.getFileSize(request.getRequestURI());
        Map<String, Integer> rangeBoundaries = parseRangeBoundaries(range, fileLength);
        return resourceManager.getPartialFileContents(request.getRequestURI(), rangeBoundaries);
    }

    private boolean isPatchPreconditionMet(String requestURI, String requestEtag) {
        byte[] currentFileContents = resourceManager.getFileContents(requestURI);
        return requestEtag.equals(sha1Encoding(currentFileContents));
    }

    private void addEtagToResponse(String requestURI, ResponseBuilder response) {
        String responseEtag = sha1Encoding(resourceManager.getFileContents(requestURI));
        response.addHeader("ETag: " + responseEtag);
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
}
