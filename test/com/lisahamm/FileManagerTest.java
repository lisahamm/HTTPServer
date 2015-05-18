package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
    private FileManager fileManager;
    private String directoryPath = "./../cob_spec/public";
    private String file1Name = "file1";
    private String file2Name = "file200";


    @Before
    public void setUp() throws Exception {
        fileManager = new FileManager(directoryPath);
    }

    @Test
    public void testReturnsTrueIfFileExistsInDirectory() throws Exception {
        FileManager fileManager = new FileManager(directoryPath);
        assertTrue(fileManager.isFileFound(file1Name));
    }

    @Test
    public void testReturnsFalseIfFileDoesNotExistInDirectory() throws Exception {
        FileManager fileManager = new FileManager(directoryPath);
        assertFalse(fileManager.isFileFound(file2Name));
    }

    @Test
    public void testReturnsListOfDirectoryFiles() throws Exception {
        List<String> files = fileManager.buildDirectoryContents();
        assertEquals("file1", files.get(0));
    }

    @Test
    public void testItAddsLinksToFileNames() throws Exception {
        List<String> files = fileManager.buildDirectoryContents();
        String directoryLinks = fileManager.addHtmlLinks(files);
        String file1Link = "<a href=\"/file1\">file1</a>";
        assertTrue(directoryLinks.contains(file1Link));
    }

}
