package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PublicFileManagerTest {
    private PublicFileManager fileManager;
    private String directoryPath = "./../cob_spec/public";
    private String file1Name = "file1";
    private String file2Name = "file200";


    @Before
    public void setUp() throws Exception {
        fileManager = new PublicFileManager(directoryPath);
    }

    @Test
    public void testReturnsTrueIfFileExistsInDirectory() throws Exception {
        assertTrue(fileManager.isFileFound(file1Name));
    }

    @Test
    public void testReturnsFalseIfFileDoesNotExistInDirectory() throws Exception {
        assertFalse(fileManager.isFileFound(file2Name));
    }

    @Test
    public void testReturnsListOfDirectoryFiles() throws Exception {
        List<String> files = fileManager.buildDirectoryContents();
        assertEquals("file1", files.get(0));
    }

}
