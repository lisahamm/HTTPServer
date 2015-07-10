package server.application.controllers;

import server.core.requests.Request;
import server.core.response.ResponseBuilder;

public interface Controller {
    void handleGet(Request request, ResponseBuilder response);
    void handlePost(Request request, ResponseBuilder response);
    void handlePut(Request request, ResponseBuilder response);
    void handlePatch(Request request, ResponseBuilder response);
    void handleDelete(Request request, ResponseBuilder response);
    void handleOptions(Request request, ResponseBuilder response);
    void handleHead(Request request, ResponseBuilder response);
}
