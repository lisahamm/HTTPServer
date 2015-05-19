package com.lisahamm;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {
    private Map<String,String> parsedRequestComponents = new HashMap<>();

    public RequestParser() {
    }

    public HTTPRequest generateParsedRequest(String rawRequest) {
        parseRequest(rawRequest);
        return new HTTPRequest(parsedRequestComponents);
    }

    private void parseRequest(String rawRequest) {
        String request = rawRequest.trim();
        String[] messageComponents = request.split(" ", 3);

        parsedRequestComponents.put("requestMethod", messageComponents[0]);
        parseRequestURI(messageComponents[1]);
        messageComponents = messageComponents[2].split("\r\n", 2);
        parsedRequestComponents.put("httpVersion", messageComponents[0]);
        if (messageComponents.length > 1) {
            messageComponents = messageComponents[1].split("\r\n\r\n");
            parsedRequestComponents.put("headers", messageComponents[0]);
            if (messageComponents.length > 1) {
                parsedRequestComponents.put("body", messageComponents[messageComponents.length - 1]);
            }
        }
    }

    private void parseRequestURI(String uri) {
        String decodedRequestURI = decodeRequestURI(uri);
        if (decodedRequestURI.contains("?")) {
            parsedRequestComponents.put("requestURI", getUriWithoutParams(decodedRequestURI));
            parsedRequestComponents.put("params", getRequestParams(decodedRequestURI));
        } else {
            parsedRequestComponents.put("requestURI", decodedRequestURI);
        }
    }

    private String decodeRequestURI(String uri) {
        String decodedRequestURI;
        try {
            decodedRequestURI = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            decodedRequestURI = uri;
        }
        return decodedRequestURI;
    }

    private String getUriWithoutParams(String uri) {
        return uri.split("\\?")[0];
    }

    private String getRequestParams(String uri) {
        String params = "";
        if (uri.contains("?")) {
            params = uri.split("\\?")[1];
        }
        return params;
    }
}
