package com.lisahamm;

public interface RequestHandler {
    boolean handle(HTTPRequest request, ResponseBuilder response);
}
