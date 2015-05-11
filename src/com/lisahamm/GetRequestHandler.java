package com.lisahamm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequestHandler implements RequestHandler {
    private Map<String, String> responseComponents = new HashMap<>();
    private String response;
    private String pathToPublicDirectory = "/Users/lisahamm/Desktop/Projects/apprenticeship/http_server/cob_spec/public";
    private Charset charset = StandardCharsets.UTF_8;

    public void handle(HTTPRequest request) {
        String responseCode = evaluateResponseCode(request.getRequestURI());
        String responsePhrase = getReasonPhrase(responseCode);
        String headers = buildHeadersString(responseCode);
        String body = buildBody(request.getRequestURI());
        responseComponents.put("httpVersion", request.getHTTPVersion());
        responseComponents.put("responseCode", responseCode);
        responseComponents.put("responsePhrase", responsePhrase);
        responseComponents.put("headers", headers);
        responseComponents.put("body", body);
        HTTPResponse httpResponse = new HTTPResponse(responseComponents);
        response = httpResponse.build();
    }

    public String getResponse() {
        return response;
    }

    private String evaluateResponseCode(String requestURI) {
        String responseCode = "";
        if(!validUriOptions().contains(requestURI)) {
            responseCode = "404";
        } else if (requestURI.equals("/redirect")) {
            responseCode = "302";
        } else {
            responseCode = "200";
        }
        return responseCode;
    }

    private String getReasonPhrase(String responseCode) {
        String reasonPhrase = "";
        if (responseCode.equals("404")) {
            reasonPhrase = "Not Found";
        } else if (responseCode.equals("302")) {
            reasonPhrase = "Found";
        } else {
            reasonPhrase = "OK";
        }
        return reasonPhrase;
    }

    private String buildHeadersString(String responseCode) {
        String headers = "";
        String CRLF = "\r\n";
        headers += "Content-Type: text/plain; charset=UTF-8";
        if (responseCode.equals("302")) {
            headers += CRLF + "Location: http://localhost:5000/";
        }
        return headers;
    }

    private String buildBody(String requestURI) {
        String body = "";
        if(requestURI.equals("/")) {
            body += buildDirectoryContentsString();
        } else if(fileExistsInDirectory(requestURI)) {
            body += buildBodyContentFromURI(requestURI, charset);
        }
        return body;
    }

    private String buildDirectoryContentsString() {
        String directoryContents = "";
        File[] directoryFiles = buildDirectoryContents();
        for(File file : directoryFiles) {
            directoryContents += file.getName() + "\r\n";
        }
        return directoryContents;
    }

    private String buildBodyContentFromURI(String requestURI, Charset encoding) {
        String uriPath = pathToPublicDirectory + requestURI;
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(uriPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, encoding);
    }


    private List<String> validUriOptions() {
        List<String> options = new ArrayList<>();

        File[] directoryFiles = buildDirectoryContents();

        for(File file : directoryFiles) {
            options.add("/" + file.getName());
        }

        options.add("/");
        options.add("/redirect");

        return options;
    }

    private boolean fileExistsInDirectory(String requestURI) {
        boolean exists = false;

        File[] directoryFiles = buildDirectoryContents();

        for(File file : directoryFiles) {
            if (("/" + file.getName()).equals(requestURI)) {
                exists = true;
            }
        }
        return exists;
    }

    private File[] buildDirectoryContents() {
        File f = new File(pathToPublicDirectory);
        File[] files = f.listFiles();
        return files;
    }
}
