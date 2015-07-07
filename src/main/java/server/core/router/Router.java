package server.core.router;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;

public interface Router {
    void invoke(Request request, ResponseBuilder response);
}
