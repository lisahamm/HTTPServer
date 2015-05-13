package com.lisahamm;

public interface ResponseBuilderInterface {
    void constructStatusLine(HTTPRequest request);
    void constructHeaders(HTTPRequest request);
    void constructBody(HTTPRequest request);
    String getResponse();
}
