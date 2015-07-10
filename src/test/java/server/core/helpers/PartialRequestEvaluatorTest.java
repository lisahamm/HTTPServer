package server.core.helpers;

import org.junit.Before;
import org.junit.Test;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PartialRequestEvaluatorTest {
    private MockHTTPRequest request;
    private Map<String, String> headers;

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        headers = new HashMap<>();
    }

    @Test
    public void testEvaluatesRangeBoundariesOfPartialRequestWithTwoNumbers() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Range", "bytes=0-4");
        request.headers = headers;

        Map<String, Integer> rangeBoundaries = PartialRequestEvaluator.getRangeBoundaries(request, 10);

        Integer startIndex = 0;
        assertEquals(startIndex, rangeBoundaries.get("startIndex"));
    }

    @Test
    public void testEvaluatesRangeBoundariesForRequestWithOnlyStartRangeValue() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Range", "bytes=4-");
        request.headers = headers;

        Map<String, Integer> rangeBoundaries = PartialRequestEvaluator.getRangeBoundaries(request, 10);

        Integer startIndex = 4;
        Integer endIndex = 9;

        assertEquals(startIndex, rangeBoundaries.get("startIndex"));
        assertEquals(endIndex, rangeBoundaries.get("endIndex"));
    }

    @Test
    public void testEvaluatesRangeBoundariesForRequestWithOnlyEndRangeValue() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/file1";
        headers.put("Range", "bytes=-6");
        request.headers = headers;

        Map<String, Integer> rangeBoundaries = PartialRequestEvaluator.getRangeBoundaries(request, 10);

        Integer startIndex = 4;
        Integer endIndex = 9;

        assertEquals(startIndex, rangeBoundaries.get("startIndex"));
        assertEquals(endIndex, rangeBoundaries.get("endIndex"));
    }
}