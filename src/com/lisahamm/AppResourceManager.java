package com.lisahamm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppResourceManager {
    private FileManager fileManager;
    private String publicDirectoryPath;
    private String pathToDataStorage;

    public AppResourceManager(FileManager fileManager, String publicDirectoryPath, String pathToDataStorage) {
        this.fileManager = fileManager;
        this.publicDirectoryPath = publicDirectoryPath;
        this.pathToDataStorage = pathToDataStorage;
    }

    public byte[] getFileContents(String uri) {
        return fileManager.getFileContents(publicDirectoryPath + uri);
    }

    public int getFileSize(String uri) {
        String filePath = publicDirectoryPath + uri;
        return (int) fileManager.getSize(filePath);
    }

    public byte[] getPartialFileContents(String uri, Map<String, Integer> rangeBoundaries) {
        String filePath = publicDirectoryPath + uri;
        Integer startIndex = rangeBoundaries.get("startIndex");
        Integer endIndex = rangeBoundaries.get("endIndex");
        byte[] partialContent = null;
        if (startIndex != null && endIndex != null) {
            partialContent = fileManager.getPartialFileContents(filePath, startIndex, endIndex + 1);
        }
        return partialContent;
    }

    public String getContentType(String requestURI) {
        String filePath = publicDirectoryPath + requestURI;
        return fileManager.getContentType(filePath);
    }

    public List<String> getPublicDirectoryContents() {
        File f = new File(publicDirectoryPath);
        File[] files = f.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public void updateResource(String uri, String data) {
        String filePath = pathToDataStorage + uri;
        fileManager.writeToFile(filePath, data);
    }

    public boolean isPublicResourceFound(String uri) {
        String filePath = publicDirectoryPath + uri;
        return fileManager.isFileFound(filePath);
    }

    public void patchResource(String uri, String data) {
        String filePath = publicDirectoryPath + uri;
        fileManager.writeToFile(filePath, data);
    }

    public String retrieveData(String uri) {
        String filePath = pathToDataStorage + uri;
        return fileManager.getTextFromFile(filePath);
    }

}
