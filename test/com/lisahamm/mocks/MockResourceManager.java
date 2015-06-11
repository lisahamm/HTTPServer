package com.lisahamm.mocks;


import com.lisahamm.ResourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MockResourceManager implements ResourceManager {

    public byte[] getFileContents(String uri) {
        return new byte[0];
    }

    public int getFileSize(String uri) {
        return 0;
    }

    public byte[] getPartialFileContents(String uri, Map<String, Integer> rangeBoundaries) {
        return new byte[0];
    }

    public String getContentType(String requestURI) {
        return null;
    }

    public List<String> getPublicDirectoryContents() {
        List<String> publicFiles = new ArrayList<>();
        publicFiles.add(0, "file1");
        return publicFiles;
    }

    public void updateResource(String uri, String data) {

    }

    public boolean isPublicResourceFound(String uri) {
        return false;
    }

    public void patchResource(String uri, String data) {

    }

    public String retrieveData(String uri) {
        return null;
    }
}
