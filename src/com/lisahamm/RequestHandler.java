package com.lisahamm;

/**
 * Created by lisahamm on 5/4/15.
 */
public class RequestHandler {
    private Parser requestParser;

    public RequestHandler(Parser requestParser) {
        requestParser = requestParser;
    }

    public String getResponse() {
        return "HTTP/1.1 200 OK\r\n";
    }
}
