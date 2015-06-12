package com.lisahamm;

import com.lisahamm.mocks.MockFileManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequestLoggerTest {
    @Test
    public void testItLogsARequest() throws Exception {
        MockFileManager fileManager = new MockFileManager();
        RequestLogger logger = new RequestLogger(fileManager);

        logger.addEntry("GET /logs HTTP/1.1\r\n");

        assertTrue(fileManager.isAppendedToFile);

    }
}
