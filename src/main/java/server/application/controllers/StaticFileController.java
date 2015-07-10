package server.application.controllers;

import server.core.Constants.HttpStatus;
import server.core.helpers.PartialRequestEvaluator;
import server.core.helpers.Sha1;
import server.core.managers.ResourceManager;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;

import java.util.Map;

public class StaticFileController extends BaseController {

    public StaticFileController(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        byte[] body;
        if (request.getHeaders().containsKey("Range")) {
            response.addStatusLine(HttpStatus.CODE206.get());
            body = getPartialContents(request);
        } else {
            response.addStatusLine(HttpStatus.CODE200.get());
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
            response.addStatusLine(HttpStatus.CODE204.get());
            addEtagToResponse(request.getRequestURI(), response);
        } else {
            response.addStatusLine(HttpStatus.CODE412.get());
        }
    }

    private byte[] getPartialContents(Request request) {
        int fileLength = resourceManager.getFileSize(request.getRequestURI());
        Map<String, Integer> rangeBoundaries = PartialRequestEvaluator.getRangeBoundaries(request, fileLength);
        return resourceManager.getPartialFileContents(request.getRequestURI(), rangeBoundaries);
    }

    private boolean isPatchPreconditionMet(String requestURI, String requestEtag) {
        byte[] currentFileContents = resourceManager.getFileContents(requestURI);
        return requestEtag.equals(Sha1.encode(currentFileContents));
    }

    private void addEtagToResponse(String requestURI, ResponseBuilder response) {
        String responseEtag = Sha1.encode(resourceManager.getFileContents(requestURI));
        response.addHeader("ETag: " + responseEtag);
    }
}
