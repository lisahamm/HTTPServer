package server.application.controllers;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;

public interface Controller {
    void execute(Request request, ResponseBuilder response);
}
