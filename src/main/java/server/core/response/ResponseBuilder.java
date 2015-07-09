package server.core.response;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    public static final String CRLF = "\r\n";
    private StringBuilder responseHeader = new StringBuilder();
    private String statusLine;
    private StringBuilder headers = new StringBuilder();
    private byte[] body;

    public void addStatusLine(String status) {
        statusLine = status;
    }

    public void addHeader(String header) {
        headers.append(header + CRLF);
    }

    public void addBody(byte[] bodyContent) {
        body = bodyContent;
    }

    public void addToBody(byte[] additionalBodyContent) {
        if (body != null) {
            Map<String, byte[]> bodyComponents = new HashMap<>();
            bodyComponents.put("component1", body);
            bodyComponents.put("component2", additionalBodyContent);
            body = combineByteArrays(bodyComponents);
        }
    }

    public String getResponseHeader() {
        constructResponseHeader();
        return responseHeader.toString();
    }

    public byte[] getBody() {
        return body;
    }

    public byte[] getEntireResponse() {
        Map<String, byte[]> responseComponents = getResponseComponents();
        return combineByteArrays(responseComponents);
    }

    private Map<String,byte[]> getResponseComponents() {
        Map<String, byte[]> responseComponents = new HashMap<>();
        if (statusLine != null) {
            byte[] header = (getResponseHeader() + CRLF).getBytes();
            responseComponents.put("header", header);
        }
        if (body != null) {
            responseComponents.put("body", body);
        }
        return responseComponents;
    }

    private void constructResponseHeader() {
        if (statusLine != null) {
            responseHeader.append(statusLine);
            if(headers.length() > 0) {
                responseHeader.append(headers);
            }
        }
    }

    private Map<String, String> responseStatuses() {
        Map<String, String> statuses = new HashMap<>();
        statuses.put("200", "200 OK");
        statuses.put("204", "204 No Content");
        statuses.put("206", "206 Partial Content");
        statuses.put("302", "302 Found");
        statuses.put("400", "400 Bad Request");
        statuses.put("401", "401 Unauthorized");
        statuses.put("404", "404 Not Found");
        statuses.put("405", "405 Method Not Allowed");
        statuses.put("412", "412 Precondition Failed");
        return statuses;
    }

    private byte[] combineByteArrays(Map<String, byte[]> byteArrays) {
        ByteArrayOutputStream combinedArray = new ByteArrayOutputStream();
        try {
            for (byte[] byteArray : byteArrays.values()) {
                combinedArray.write(byteArray);
            }
            combinedArray.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return combinedArray.toByteArray();
    }
}
