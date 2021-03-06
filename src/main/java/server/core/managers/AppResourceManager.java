package server.core.managers;

import server.application.Resources;
import server.core.configuration.Settings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppResourceManager implements ResourceManager {
    private FileManager fileManager;
    private String publicDirectory;

    public AppResourceManager(FileManager fileManager) {
        this.fileManager = fileManager;
        this.publicDirectory = Settings.publicDirectory;
    }

    public byte[] getFileContents(String uri) {
        return fileManager.getFileContents(publicDirectory + uri);
    }

    public int getFileSize(String uri) {
        String filePath = publicDirectory + uri;
        return (int) fileManager.getSize(filePath);
    }

    public byte[] getPartialFileContents(String uri, Map<String, Integer> rangeBoundaries) {
        String filePath = publicDirectory + uri;
        Integer startIndex = rangeBoundaries.get("startIndex");
        Integer endIndex = rangeBoundaries.get("endIndex");
        byte[] partialContent = null;
        if (startIndex != null && endIndex != null) {
            partialContent = fileManager.getPartialFileContents(filePath, startIndex, endIndex + 1);
        }
        return partialContent;
    }

    public String getContentType(String requestURI) {
        String filePath = publicDirectory + requestURI;
        return fileManager.getContentType(filePath);
    }

    public List<String> getPublicDirectoryContents() {
        File f = new File(publicDirectory);
        File[] files = f.listFiles();
        List<String> fileNames = new ArrayList<>();

        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    public boolean isPublicResourceFound(String uri) {
        String filePath = publicDirectory + uri;
        return fileManager.isFileFound(filePath);
    }

    public void patchResource(String uri, String data) {
        String filePath = publicDirectory + uri;
        fileManager.writeToFile(filePath, data);
    }
}
