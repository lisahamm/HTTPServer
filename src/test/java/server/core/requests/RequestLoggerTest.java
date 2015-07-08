package server.core.requests;

import server.application.Resources;
import server.mocks.MockResourceManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RequestLoggerTest {
    @Test
    public void testItLogsARequest() throws Exception {
        RequestLogger logger = new RequestLogger();
        String request = "GET /logs HTTP/1.1\r\n";
        logger.addEntry(request);

        assertTrue(Resources.logs.toString().contains(request));

    }
}
