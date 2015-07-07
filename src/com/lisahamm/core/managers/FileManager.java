package com.lisahamm.core.managers;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public interface FileManager {
    public boolean isFileFound(String filePath);

    public File getFile(String filePath);

    public void writeToFile(String filePath, String data);

    public void appendToFile(String filePath, String data);

    public byte[] getFileContents(String filePath);

    public String getTextFromFile(String filePath);

    public byte[] getPartialFileContents(String filePath, int startIndex, int endIndex);

    public String getContentType(String filePath);

    public long getSize(String filePath);
}
