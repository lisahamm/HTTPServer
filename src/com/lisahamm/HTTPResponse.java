package com.lisahamm;

import java.io.File;
import java.util.Map;

public class HTTPResponse {
    private Map<String, String> responseComponents;
    private String response;

    public HTTPResponse(Map<String, String> responseComponents) {
        this.responseComponents = responseComponents;
    }

    public void build() {
        String responseString = "";
        String CRLF = "\r\n";
        responseString += buildStatusLine();
        responseString += CRLF;
        responseString += responseComponents.get("headers");
        responseString += CRLF + CRLF;
        responseString += responseComponents.get("body");
        response = responseString;
    }

    public String getResponse() {
        return response;
    }

    private String buildStatusLine() {
        String responseString = "";
        String httpVersion = responseComponents.get("httpVersion");
        String responseCode = responseComponents.get("responseCode");
        String responsePhrase = responseComponents.get("responsePhrase");
        return responseString += httpVersion + responseCode + responsePhrase;
    }

}
