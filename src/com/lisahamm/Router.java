package com.lisahamm;

public interface Router {
    void invoke(HTTPRequest request, ResponseBuilder response);
    void registerHandler(RequestHandler handler);
}
