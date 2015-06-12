package com.lisahamm;


public class RequestLogger {
    private static String pathToDataStorage = "./../cob_spec/database";
    private FileManager fileManager = new AppFileManager();

    public void addEntry(String request) {
        String filePath = pathToDataStorage + "/logs";
        fileManager.appendToFile(filePath, request);
    }
}
