package server.core.requests;

import org.junit.Before;
import org.junit.Test;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HeaderParserTest {
    private MockHTTPRequest request;
    private Map<String, String> headers;
    private ResponseBuilder response;

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
        headers = new HashMap<>();
    }

    @Test
    public void testItParsesRangeFromRequestHeader() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Range", "bytes=0-4");
        request.headers = headers;

        String range = HeaderParser.parseRange(request);

        assertEquals("0-4", range);
    }

    @Test
    public void testItParsesAuthorizationHeader() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        request.headers = headers;

        String authorizationValue = HeaderParser.parseAuthorizationHeader(request);

        assertEquals("YWRtaW46aHVudGVyMg==", authorizationValue);
    }
}