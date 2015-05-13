//package com.lisahamm;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GetResponseBuilder implements ResponseBuilderInterface {
//    private String pathToPublicDirectory = "./../cob_spec/public";
//    private Charset charset = StandardCharsets.UTF_8;
//    private String CRLF = "\r\n";
//    private String response = "";
//
//    public void constructStatusLine(HTTPRequest request) {
//        String statusLine = "";
//        String httpVersion = request.getHTTPVersion();
//        String responseCode = evaluateResponseCode(request.getRequestURI());
//        String responsePhrase = getReasonPhrase(responseCode);
//        statusLine += httpVersion + " " + responseCode + " " + responsePhrase;
//        response += statusLine + CRLF;
//    }
//
//    public void constructHeaders(HTTPRequest request) {
//        String headers = "";
//        headers += "Content-Type: text/html; charset=UTF-8" + CRLF;
//        if (request.getRequestURI().equals("/redirect")) {
//            headers += "Location: http://localhost:5000/" + CRLF;
//        }
//        response += headers;
//    }
//
//    public void constructBody(HTTPRequest request) {
//        String requestURI = request.getRequestURI();
//        String body = "";
//        if(requestURI.equals("/")) {
//            body += buildDirectoryContentsString();
//        } else if(fileExistsInDirectory(requestURI)) {
//            body += buildBodyContentFromURI(requestURI, charset);
//        }
//        response += CRLF + body;
//    }
//
//    public String getResponse() {
//        return response;
//    }
//
//    private String evaluateResponseCode(String requestURI) {
//        String responseCode = "";
//        if(!validUriOptions().contains(requestURI)) {
//            responseCode = "404";
//        } else if (requestURI.equals("/redirect")) {
//            responseCode = "302";
//        } else {
//            responseCode = "200";
//        }
//        return responseCode;
//    }
//
//    private String getReasonPhrase(String responseCode) {
//        String reasonPhrase = "";
//        if (responseCode.equals("404")) {
//            reasonPhrase = "Not Found";
//        } else if (responseCode.equals("302")) {
//            reasonPhrase = "Found";
//        } else {
//            reasonPhrase = "OK";
//        }
//        return reasonPhrase;
//    }
//
//    private List<String> validUriOptions() {
//        List<String> options = new ArrayList<>();
//
//        File[] directoryFiles = buildDirectoryContents();
//
//        for(File file : directoryFiles) {
//            options.add("/" + file.getName());
//        }
//
//        options.add("/");
//        options.add("/redirect");
//
//        return options;
//    }
//
//    private File[] buildDirectoryContents() {
//        File f = new File(pathToPublicDirectory);
//        File[] files = f.listFiles();
//        return files;
//    }
//
//    private String buildDirectoryContentsString() {
//        String directoryContents = "<p>";
//        File[] directoryFiles = buildDirectoryContents();
//        for(File file : directoryFiles) {
//            String fileName = file.getName();
//            String filePath = "/" + fileName;
//            directoryContents += "<a href=\"" + filePath + "\">" + fileName + "</a>" + "\r\n";
//
//        }
//        directoryContents += "</body>";
//        return directoryContents;
//    }
//
//    private String buildBodyContentFromURI(String requestURI, Charset encoding) {
//        String uriPath = pathToPublicDirectory + requestURI;
//        byte[] encoded = new byte[0];
//        try {
//            encoded = Files.readAllBytes(Paths.get(uriPath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new String(encoded, encoding);
//    }
//
//    private boolean fileExistsInDirectory(String requestURI) {
//        boolean exists = false;
//
//        File[] directoryFiles = buildDirectoryContents();
//
//        for(File file : directoryFiles) {
//            if (("/" + file.getName()).equals(requestURI)) {
//                exists = true;
//            }
//        }
//        return exists;
//    }
//}
