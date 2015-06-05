package com.lisahamm;

public interface FileManager {
    boolean isFileFound(String fileName);
    byte[] getFileContents(String requestURI);
    byte[] getPartialFileContents(String requestURI, String range);
    String getContentType(String requestURI);
}
