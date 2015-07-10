package server.core.helpers;

import org.junit.Before;
import org.junit.Test;
import server.mocks.MockHTTPRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class BasicAuthTest {
    private MockHTTPRequest request;
    private Map<String, String> headers;
    private String logsUri = "/logs";
    private String validUserID = "admin";
    private String validPassword = "hunter2";

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        headers = new HashMap<>();
    }

    @Test
    public void testItValidatesCorrectAuthorizationCredentials() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        headers.put("Authorization", "Basic YWRtaW46aHVudGVyMg==");
        request.headers = headers;

        assertTrue(BasicAuth.isAuthorized(request, validUserID, validPassword));
    }

    @Test
    public void testItDoesNotAuthorizeARequestWithInvalidCredentials() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        headers.put("Authorization", "Basic YWRtaW46dGVzdA==");
        request.headers = headers;

        assertFalse(BasicAuth.isAuthorized(request, validUserID, validPassword ));
    }

    @Test
    public void testItDoesNotAuthorizeARequestWithoutAuthorizationCredentials() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = logsUri;
        request.headers = headers;

        assertFalse(BasicAuth.isAuthorized(request, validUserID, validPassword));
    }
}