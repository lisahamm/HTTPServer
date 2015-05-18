package com.lisahamm;

public interface FileManager {
    boolean isFileFound(String fileName);
    byte[] getFileContents(String requestURI);
    String getContentType(String requestURI);
}
