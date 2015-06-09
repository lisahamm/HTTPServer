package com.lisahamm;

import java.io.File;

public interface FileManager {
    boolean isFileFound(String fileName);
    File getFile(String requestURI);
    void overwriteFile(String requestURI, String newContents);
    byte[] getFileContents(String requestURI);
    byte[] getPartialFileContents(String requestURI, String range);
    String getContentType(String requestURI);
}
