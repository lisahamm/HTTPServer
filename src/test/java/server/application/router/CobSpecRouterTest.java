package server.application.router;

import org.junit.Before;
import org.junit.Test;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import static org.junit.Assert.*;

public class CobSpecRouterTest {
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private String responseStatus404 = "HTTP/1.1 404 Not Found\r\n";
    private String responseStatus400 = "HTTP/1.1 400 Bad Request\r\n";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed\r\n";

    private MockHTTPRequest request;
    private ResponseBuilder response;
    private CobSpecRouter router;

    @Before
    public void setUp() throws Exception {
        request = new MockHTTPRequest();
        response = new ResponseBuilder();
        router = new CobSpecRouter(new CobSpecRoutes());
    }

    @Test
    public void testRoutesRequestWithValidRouteToCorrespondingController() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/form";

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testRoutesRequestWithInvalidUri() throws Exception {
        request.requestMethod = "GET";
        request.requestURI = "/invalid";

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus404));
    }

    @Test
    public void testRoutesRequestWithValidUriAndHTTPMethodNotAllowed() throws Exception {
        request.requestMethod = "DELETE";
        request.requestURI = "/file1";

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testRoutesRequestWithHttpMethodUnsupportedByServer() throws Exception {
        request.requestMethod = "";
        request.requestURI = "";

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus400));
    }
}