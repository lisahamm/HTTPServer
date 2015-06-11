package com.lisahamm;

import java.util.List;
import java.util.Map;

public interface ResourceManager {
    public byte[] getFileContents(String uri);

    public int getFileSize(String uri);

    public byte[] getPartialFileContents(String uri, Map<String, Integer> rangeBoundaries);

    public String getContentType(String requestURI);

    public List<String> getPublicDirectoryContents();

    public void updateResource(String uri, String data);

    public boolean isPublicResourceFound(String uri);

    public void patchResource(String uri, String data);

    public String retrieveData(String uri);
}
