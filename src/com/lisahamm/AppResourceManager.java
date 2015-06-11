package com.lisahamm;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AppResourceManager {
    private Filer fileManager;
    private String publicDirectoryPath;
    private String pathToDataStorage;

    public AppResourceManager(Filer fileManager, String publicDirectoryPath, String pathToDataStorage) {
        this.fileManager = fileManager;
        this.publicDirectoryPath = publicDirectoryPath;
        this.pathToDataStorage = pathToDataStorage;
    }

    public byte[] getFileContents(String uri) {
        return fileManager.getFileContents(publicDirectoryPath + uri);
    }

    public byte[] getPartialFileContents(String uri, String range) {
        String filePath = publicDirectoryPath + uri;

        int fileLength = (int) fileManager.getSize(filePath);

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

        return fileManager.getPartialFileContents(filePath, startIndex, endIndex + 1);
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



}
