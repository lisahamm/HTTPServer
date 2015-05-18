package com.lisahamm;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PublicFileManager implements DirectoryManager, FileManager {

    private String directoryPath;

    public PublicFileManager(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public boolean isFileFound(String fileName) {
        List<String> directoryContents = buildDirectoryContents();
        return directoryContents.contains(fileName);
    }

    public List<String> buildDirectoryContents() {
        File f = new File(directoryPath);
        File[] files = f.listFiles();
        List<String> fileNames = new ArrayList<>();

        for(File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public byte[] getFileContents(String requestURI) {
        String uriPath = directoryPath + requestURI;
        byte[] data = "".getBytes();
        try {
            data = Files.readAllBytes(Paths.get(uriPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getContentType(String requestURI) {
        String filePath = directoryPath + requestURI;
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(filePath);
        if (type == null) {
            type = "text/plain";
        }
        return type;
    }
}
