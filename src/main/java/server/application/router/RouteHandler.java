package server.application.router;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;

public interface RouteHandler {
    void invoke(Request request, ResponseBuilder response);
}
