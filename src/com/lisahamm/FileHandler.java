package com.lisahamm;

public class FileHandler implements RequestHandler {
    private FileManager fileManager;

    public FileHandler(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public boolean handle(HTTPRequest request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();
        String fileName = requestURI.substring(1);

        if (fileManager.isFileFound(fileName)) {
            switch(requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    String mimeType = fileManager.getContentType(requestURI);
                    response.addHeader("Content-Type: " + mimeType);
                    response.addBody(fileManager.getFileContents(requestURI));
                    break;
                default:
                    response.addStatusLine("405");
                    break;
            }
            return true;
        }
        return false;
    }
}
