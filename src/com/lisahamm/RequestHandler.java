package com.lisahamm;

public interface RequestHandler {
    void handle(HTTPRequest request);
    String getResponse();
}
