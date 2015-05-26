package com.lisahamm;

public interface RequestHandler {
    boolean handle(Request request, ResponseBuilder response);
}
