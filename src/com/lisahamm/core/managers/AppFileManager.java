package com.lisahamm.core.managers;

import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppFileManager implements FileManager {

    public boolean isFileFound(String filePath) {
        File f = new File(filePath);
        return f.exists() && !f.isDirectory();
    }

    public File getFile(String filePath) {
        return new File(filePath);
    }

    public void writeToFile(String filePath, String data) {
        try {
            File file = new File(filePath);
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.print(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String filePath, String data) {
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file, true);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getFileContents(String filePath) {
        byte[] data = "".getBytes();
        try {
            data = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getTextFromFile(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder fileContents = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                fileContents.append(line);
                fileContents.append(System.lineSeparator());
                line = reader.readLine();
            }
            return fileContents.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] getPartialFileContents(String filePath, int startIndex, int endIndex) {
        byte[] fileContents = getFileContents(filePath);
        return Arrays.copyOfRange(fileContents, startIndex, endIndex);
    }

    public String getContentType(String filePath) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(filePath);
        if (type == null) {
            type = "text/plain";
        }
        return type;
    }

    public long getSize(String filePath) {
        File file = new File(filePath);
        return file.length();
    }

    public List<String> buildDirectoryContents(String directoryPath) {
        File f = new File(directoryPath);
        File[] files = f.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }
}
