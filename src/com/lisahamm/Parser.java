package com.lisahamm;

public interface Parser {
    void parseRequest(Request request);
    void updateWithParsedData();
}
