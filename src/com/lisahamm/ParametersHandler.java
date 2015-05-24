package com.lisahamm;

import java.util.Map;

public class ParametersHandler implements RequestHandler {
    private static final String uri = "/parameters";

    public boolean handle(Request request, ResponseBuilder response) {
        String requestMethod = request.getRequestMethod();
        String requestURI = request.getRequestURI();
        Map<String, String> requestParams = request.getParams();

        if (requestURI.equals(this.uri)) {
            switch (requestMethod) {
                case "GET":
                    response.addStatusLine("200");
                    response.addHeader("Content-Type: text/plain");
                    response.addBody(paramsHashToString(requestParams).getBytes());
                    break;
                default:
                    response.addStatusLine("405");
                    response.addHeader("Allow: GET, HEAD, POST, OPTIONS, PUT");
                    break;
            }
            return true;
        }
        return false;
    }

    private String paramsHashToString(Map<String, String> paramsHash) {
        String paramString = "";
        for (Map.Entry<String, String> entry : paramsHash.entrySet())
        {
            paramString += entry.getKey() + " = " + entry.getValue() + "\r\n";
        }
        return paramString;
    }
}
