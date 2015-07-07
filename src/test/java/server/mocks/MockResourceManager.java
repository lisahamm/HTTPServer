package server.mocks;


import server.core.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockResourceManager implements ResourceManager {
    private String resourceData = "data=foo";
    private String patchFileContent = "default content";
    public boolean isFileRequested;
    public boolean isPartialContentRequested;
    public boolean isUpdated;
    public boolean isAppendedToResource;

    public MockResourceManager() {
        this.isFileRequested = false;
        this.isPartialContentRequested = false;
        this.isUpdated = false;
        this.isAppendedToResource = false;
    }

    public byte[] getFileContents(String uri) {
        isFileRequested = true;
        if (uri.equals("/patch-content.txt")) {
            return patchFileContent.getBytes();
        }
        return "file contents".getBytes();
    }

    public int getFileSize(String uri) {
        return 0;
    }

    public byte[] getPartialFileContents(String uri, Map<String, Integer> rangeBoundaries) {
        isPartialContentRequested = true;
        return "data=".getBytes();
    }

    public String getContentType(String requestURI) {
        return "text/plain";
    }

    public List<String> getPublicDirectoryContents() {
        List<String> publicFiles = new ArrayList<>();
        publicFiles.add(0, "file1");
        return publicFiles;
    }

    public void updateResource(String uri, String data) {
        this.isUpdated = true;
        resourceData = data;
    }

    public void appendToResource(String uri, String data) {
        this.isAppendedToResource = true;
    }

    public boolean isPublicResourceFound(String uri) {
        if (uri.equals("/file1") || uri.equals("/patch-content.txt")) {
            return true;
        }
        return false;
    }

    public void patchResource(String uri, String data) {
        patchFileContent = data;
    }

    public String retrieveData(String uri) {
        return resourceData;
    }
}
