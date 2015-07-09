package server.core.requests;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestParser implements Parser {
    private Map<String,String> parsedRequestComponents = new HashMap<>();
    private String uriWithoutParams;
    private Map<String, String> params;
    private Map<String, String> headers;

    public RequestParser() {
        uriWithoutParams = "";
        params = new HashMap<>();
        headers = new HashMap<>();
    }

    public Request generateParsedRequest(String rawRequest) {
        parseRequest(rawRequest);
        return new HTTPRequest(parsedRequestComponents, params, headers);
    }

    private void parseRequest(String rawRequest) {
        String request = rawRequest.trim();
        String[] messageComponents = request.split("\r\n", 2);
        parseRequestLine(messageComponents[0]);
        if (messageComponents.length > 1) {
            messageComponents = messageComponents[1].split("\r\n\r\n");

            headers = getHeadersHash(messageComponents[0]);

            if (messageComponents.length > 1) {
                parsedRequestComponents.put("body", messageComponents[messageComponents.length - 1]);
            }
        }
    }

    private void parseRequestLine(String requestLine) {
        String[] messageComponents = requestLine.split(" ", 3);
        if(messageComponents.length != 3) {
            parsedRequestComponents.put("requestMethod", "");
            parsedRequestComponents.put("requestURI", "");
            parsedRequestComponents.put("httpVersion", "");
        } else {
            parsedRequestComponents.put("requestMethod", messageComponents[0]);
            parseRequestURI(messageComponents[1]);
            parsedRequestComponents.put("httpVersion", messageComponents[2]);
        }
    }

    private void parseRequestURI(String uri) {
        if (uri.contains("?")) {
            String[] uriComponents = uri.split("\\?", 2);
            uriWithoutParams = decodeString(uriComponents[0]);
            params = paramsToMap(uriComponents[1]);
            parsedRequestComponents.put("requestURI", uriWithoutParams);
        } else {
            parsedRequestComponents.put("requestURI", decodeString(uri));
            params = new HashMap<>();
        }
    }

    private String decodeString(String encoded) {
        String decodedRequestURI;
        try {
            decodedRequestURI = URLDecoder.decode(encoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            decodedRequestURI = encoded;
        }
        return decodedRequestURI;
    }

    private Map<String, String> paramsToMap(String encodedParams){
        Map<String, String> paramsHash = new HashMap<>();
        String[] paramsArray = encodedParams.split("&");
        for (String param : paramsArray) {
            String[] paramComponents = param.split("=");
            paramsHash.put(decodeString(paramComponents[0]), decodeString(paramComponents[1]));
        }
        return paramsHash;
    }

    private HashMap<String, String> getHeadersHash(String headers) {
        HashMap<String, String> parsedHeaders = new HashMap<>();

        String[] splitHeaders = headers.split("\r\n");
        for (String header : splitHeaders) {
            String[] splitHeader = header.split(":");
            parsedHeaders.put(splitHeader[0].trim(), splitHeader[1].trim());
        }
        return parsedHeaders;
    }
}
