package com.lisahamm;

import java.util.List;

public interface DirectoryManager {
    boolean isFileFound(String fileName);
    byte[] getFileContents(String requestURI);
    String getContentType(String requestURI);
}
