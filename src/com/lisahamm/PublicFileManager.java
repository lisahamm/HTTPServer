package com.lisahamm;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public File getFile(String requestURI) {
        return new File(directoryPath + requestURI);
    }

    public void overwriteFile(String requestURI, String newContents) {
        try {
            FileWriter writer = new FileWriter(getFile(requestURI), false);
            writer.write(newContents);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public byte[] getPartialFileContents(String requestURI, String range) {
        byte[] fileContents = getFileContents(requestURI);
        int fileLength = fileContents.length;

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

        return Arrays.copyOfRange(fileContents, startIndex, endIndex + 1);
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
