package com.lisahamm;


public class RequestLogger implements Logger {
    private static String pathToDataStorage = "./../cob_spec/database/logs";
    private FileManager fileManager;

    public RequestLogger(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void addEntry(String request) {
        String filePath = pathToDataStorage;
        fileManager.appendToFile(filePath, request);
    }
}
