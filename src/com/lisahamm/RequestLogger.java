package com.lisahamm;


public class RequestLogger {
    private static String pathToDataStorage = "./../cob_spec/database";
    private FileManager fileManager;

    public RequestLogger(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void addEntry(String request) {
        String filePath = pathToDataStorage + "/logs";
        fileManager.appendToFile(filePath, request);
    }
}
