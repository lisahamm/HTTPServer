package server.core.requests;
import org.junit.*;

import static org.junit.Assert.assertEquals;


public class RequestParserTest {
    private RequestParser requestParser;
    private String rawRequest;
    private String rawRequestEncoded;

    @Before
    public void setUp() throws Exception {
        requestParser = new RequestParser();
        rawRequest = "GET / HTTP/1.1\r\nHost: 0.0.0.0\r\n\r\nBody";
        rawRequestEncoded = "GET /file1%20copy HTTP/1.1\r\nHost: 0.0.0.0\r\n";
    }

    @Test
    public void testRequestMethodIsParsed() throws Exception {
        Request request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("GET", request.getRequestMethod());
    }

    @Test
    public void testRequestUriIsParsed() throws Exception {
        Request request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("/", request.getRequestURI());
    }

    @Test
    public void testEncodedRequestUriIsParsed() throws Exception {
        Request request = requestParser.generateParsedRequest(rawRequestEncoded);
        assertEquals("/file1 copy", request.getRequestURI());
    }

    @Test
    public void testRequestHTTPVersionIsParsed() throws Exception {
        Request request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("HTTP/1.1", request.getHTTPVersion());
    }

    @Test
    public void testRequestHeaderIsParsed() throws Exception {
        String headerKey = "Host";
        String headerValue = "0.0.0.0";
        Request request = requestParser.generateParsedRequest(rawRequest);
        assertEquals(headerValue, request.getHeaders().get(headerKey));
    }

    @Test
    public void testRequestBodyIsParsed() throws Exception {
        Request request = requestParser.generateParsedRequest(rawRequest);
        assertEquals("Body", request.getBody());
    }

    @Test
    public void testRequestParamIsParsed() throws Exception {
        String paramKey = "variable_1";
        String paramValue = "Operators";
        String assignmentOperator = "=";
        String paramKeyValuePair = paramKey + assignmentOperator + paramValue;
        String rawRequestWithParam = generateRawGetParamsRequest(paramKeyValuePair);
        Request requestWithParam = requestParser.generateParsedRequest(rawRequestWithParam);
        assertEquals(paramValue, requestWithParam.getParams().get(paramKey));
    }

    @Test
    public void testRequestParamsAreParsed() throws Exception {
        String param1Key = "variable_1";
        String param1Value = "Operators";
        String param2Key = "variable_2";
        String param2Value = "stuff";
        String assignmentOperator = "=";
        String params = param1Key + assignmentOperator + param1Value;
        params += "&" + param2Key + assignmentOperator + param2Value;
        String rawRequestWithParams = generateRawGetParamsRequest(params);
        Request requestWithParams = requestParser.generateParsedRequest(rawRequestWithParams);
        assertEquals(param1Value, requestWithParams.getParams().get(param1Key));
        assertEquals(param2Value, requestWithParams.getParams().get(param2Key));
    }

    @Test
    public void testEncodedParamsAreParsed() throws Exception {
        String param1Key = "variable_1";
        String param1Value = "Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?";
        String param2Key = "variable_2";
        String param2Value = "stuff";
        String encodedParams = "variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff";
        String rawRequestWithParams = generateRawGetParamsRequest(encodedParams);
        Request requestWithParams = requestParser.generateParsedRequest(rawRequestWithParams);
        assertEquals(param1Value, requestWithParams.getParams().get(param1Key));
        assertEquals(param2Value, requestWithParams.getParams().get(param2Key));
    }

    private String generateRawGetParamsRequest(String params) {
        String space = " ";
        StringBuilder rawRequest = new StringBuilder("GET /parameters?");
        rawRequest.append(params + space);
        rawRequest.append("HTTP/1.1");
        return rawRequest.toString();
    }
}
