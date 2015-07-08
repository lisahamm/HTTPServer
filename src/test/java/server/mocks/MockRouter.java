package server.mocks;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.router.Router;

public class MockRouter implements Router {
    public boolean isInvoked = false;

    public void invoke(Request request, ResponseBuilder response) {
        isInvoked = true;
    }
}
