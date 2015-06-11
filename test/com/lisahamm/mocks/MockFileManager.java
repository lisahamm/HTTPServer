package com.lisahamm.mocks;

import com.lisahamm.Filer;
import java.io.File;

public class MockFileManager implements Filer {
    public boolean fileFound = false;
    public boolean getFile = false;
    public boolean isWrittenToFile = false;
    public boolean isfileContentRequested = false;
    public boolean isFileTextRequested = false;
    public boolean isPartialContentRequested = false;
    public boolean isContentTypeRequested = false;
    public boolean isSizeRequested = false;
    public boolean isAppendedToFile = true;

    public boolean isFileFound(String filePath) {
        fileFound = true;
        return fileFound;
    }

    public File getFile(String filePath) {
        getFile = true;
        return null;
    }

    public void writeToFile(String filePath, String data) {
        isWrittenToFile = true;
    }

    public void appendToFile(String filePath, String data) {
        isAppendedToFile = true;
    }

    public byte[] getFileContents(String filePath) {
        isfileContentRequested = true;
        return new byte[0];
    }

    public String getTextFromFile(String filePath) {
        isFileTextRequested = true;
        return "";
    }

    public byte[] getPartialFileContents(String filePath, int startIndex, int endIndex) {
        isPartialContentRequested = true;
        return new byte[0];
    }

    public String getContentType(String filePath) {
        isContentTypeRequested = true;
        return null;
    }

    public long getSize(String filePath) {
        isSizeRequested = true;
        return 0;
    }
}

