package com.lisahamm;

public interface ResponseBuilder {
    void constructStatusLine(HTTPRequest request);
    void constructHeaders(HTTPRequest request);
    void constructBody(HTTPRequest request);
    String getResponse();
}
