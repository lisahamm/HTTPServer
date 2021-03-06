package server.core.requests;

import java.util.Map;

public class HTTPRequest implements Request {
    private String requestMethod;
    private String requestURI;
    private String httpVersion;
    private Map<String, String> params;
    private Map<String, String> headers;
    private String body;

    public HTTPRequest(Map<String, String> parsedRequestComponents, Map<String,String> params, Map<String,String> headers) {
        this.requestMethod = parsedRequestComponents.get("requestMethod");
        this.requestURI = parsedRequestComponents.get("requestURI");
        this.httpVersion = parsedRequestComponents.get("httpVersion");
        this.params = params;
        this.headers = headers;
        this.body = parsedRequestComponents.get("body");
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getHTTPVersion() {
        return httpVersion;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

}
