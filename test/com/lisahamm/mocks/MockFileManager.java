package com.lisahamm.mocks;

import com.lisahamm.FileManager;

import java.io.File;

public class MockFileManager implements FileManager {
    public boolean isFileFound(String fileName) {
        if (fileName.equals("file1") || fileName.equals("image.gif") || fileName.equals("patch-content.txt")) {
            return true;
        }
        return false;
    }

    public File getFile(String requestURI) {
        return new File(requestURI);
    }

    public void overwriteFile(String requestURI, String newContents) {

    }

    public byte[] getFileContents(String requestURI) {
        if (requestURI.contains("patch-content.txt")) {
            return "default content".getBytes();
        } else {
            return "file1 contents".getBytes();
        }
    }

    public byte[] getPartialFileContents(String requestURI, String range) {
        return "file1".getBytes();
    }

    public String getContentType(String requestURI) {
        if (requestURI.contains("image.gif")) {
            return "image/gif";
        }
        return "text/plain";
    }


    public void writeToFile(String path, String data) {

    }

    public String getTextFromFile(String filePath) {
        return null;
    }
}

