package server.application.router;

import org.junit.Test;
import server.core.response.ResponseBuilder;
import server.mocks.MockHTTPRequest;
import server.mocks.MockResourceManager;

import static org.junit.Assert.*;

public class CobSpecRouterTest {
    private String responseStatus200 = "HTTP/1.1 200 OK\r\n";
    private String responseStatus404 = "HTTP/1.1 404 Not Found\r\n";
    private String responseStatus400 = "HTTP/1.1 400 Bad Request\r\n";
    private String responseStatus405 = "HTTP/1.1 405 Method Not Allowed\r\n";

    @Test
    public void testItRoutesRequestWithValidRouteToCorrespondingController() throws Exception {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = "GET";
        request.requestURI = "/form";
        ResponseBuilder response = new ResponseBuilder();
        CobSpecRouter router = new CobSpecRouter(new MockResourceManager());

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus200));
    }

    @Test
    public void testItRoutesRequestWithInvalidUri() throws Exception {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = "GET";
        request.requestURI = "/invalid";
        ResponseBuilder response = new ResponseBuilder();
        CobSpecRouter router = new CobSpecRouter(new MockResourceManager());

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus404));
    }

    @Test
    public void testRoutesRequestWithValidUriAndHTTPMethodThatIsNotAllowed() throws Exception {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = "DELETE";
        request.requestURI = "/file1";
        ResponseBuilder response = new ResponseBuilder();
        CobSpecRouter router = new CobSpecRouter(new MockResourceManager());

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus405));
    }

    @Test
    public void testInvokeWithRequestWithHttpMethodUnsupportedByServer() throws Exception {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = "";
        request.requestURI = "";
        ResponseBuilder response = new ResponseBuilder();
        CobSpecRouter router = new CobSpecRouter(new MockResourceManager());

        router.invoke(request, response);

        assertTrue(response.getResponseHeader().contains(responseStatus400));
    }
}