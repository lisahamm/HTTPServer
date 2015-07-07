package com.lisahamm.core.requests;

import com.lisahamm.core.requests.RequestLogger;
import com.lisahamm.mocks.MockResourceManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequestLoggerTest {
    @Test
    public void testItLogsARequest() throws Exception {
        MockResourceManager resourceManager = new MockResourceManager();
        RequestLogger logger = new RequestLogger(resourceManager);

        logger.addEntry("GET /logs HTTP/1.1\r\n");

        assertTrue(resourceManager.isAppendedToResource);

    }
}
