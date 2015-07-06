package com.lisahamm;

import com.lisahamm.mocks.MockFileManager;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class AppResourceManagerTest {
    private MockFileManager fileManager;
    private AppResourceManager resourceManager;
    private String uri = "/file";

    @Before
    public void setUp() throws Exception {
        fileManager = new MockFileManager();
        String publicDirectoryPath = "/public";
        String pathToDataStorage = "/database";
        resourceManager = new AppResourceManager(fileManager);
    }

    @Test
    public void testItDelegatesToFileManagerToRetrieveFileContents() throws Exception {
        resourceManager.getFileContents(uri);

        assertTrue(fileManager.isfileContentRequested);
    }

    @Test
    public void testItDelegatesToFileManagerToGetPartialFileContents() throws Exception {
        Map<String, Integer> rangeBoundaries = new HashMap<>();
        rangeBoundaries.put("startIndex", 1);
        rangeBoundaries.put("endIndex", 2);

        resourceManager.getPartialFileContents(uri, rangeBoundaries);

        assertTrue(fileManager.isPartialContentRequested);
    }

    @Test
    public void testItDelegatesToFileManagerToGetFileContentType() throws Exception {
        resourceManager.getContentType(uri);

        assertTrue(fileManager.isContentTypeRequested);
    }

    @Test
    public void testItDelegatesToFileManagerToUpdateAResource() throws Exception {
        resourceManager.updateResource(uri, "data");

        assertTrue(fileManager.isWrittenToFile);
    }

    @Test
    public void testItDelegatesToFileManagerToCheckIfPublicResourceExists() throws Exception {
        resourceManager.isPublicResourceFound(uri);

        assertTrue(fileManager.fileFound);
    }

    @Test
    public void testItDelegatesToFileManagerToPatchResource() throws Exception {
        resourceManager.patchResource(uri, "data");

        assertTrue(fileManager.isWrittenToFile);
    }

    @Test
    public void testItDelegatesToFileManagerToRetrieveResourceData() throws Exception {
        resourceManager.retrieveData(uri);

        assertTrue(fileManager.isFileTextRequested);
    }

    @Test
    public void testItDelegatesToFileManagerToGetFileSize() throws Exception {
        resourceManager.getFileSize(uri);

        assertTrue(fileManager.isSizeRequested);
    }
}
