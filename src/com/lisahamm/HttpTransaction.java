package com.lisahamm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpTransaction extends Thread {
    private ConnectionIO clientConnection;
    private RequestParser parser;
    private ResponseBuilder responseBuilder;
    private Router router;

    public HttpTransaction(ConnectionIO clientConnection, RequestParser parser,
                           ResponseBuilder responseBuilder, Router router) {
        this.clientConnection = clientConnection;
        this.parser = parser;
        this.responseBuilder = responseBuilder;
        this.router = router;
    }

    public void run() {
        try {
            openClientConnectionIO();

            String rawRequest = readInRawRequest();

            if (requestIsValid(rawRequest)) {
                HTTPRequest request = parser.generateParsedRequest(rawRequest);
                router.invoke(request, responseBuilder);
                sendResponse(responseBuilder);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                clientConnection.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void sendResponse(ResponseBuilder response) throws IOException {
        String responseHeader = response.getResponseHeader();
        byte[] body = response.getBody();

        clientConnection.writeToOutputStream(responseHeader);

        if (body != null) {
            clientConnection.writeToOutputStream(response.CRLF);
            clientConnection.writeToOutputStream(body);
        }
    }

    private void openClientConnectionIO() throws IOException {
        clientConnection.openInputReader();
        clientConnection.openOutputStream();
    }

    private String readInRawRequest() throws IOException {
        StringBuilder rawRequest = new StringBuilder();
        do {
            rawRequest.append((char) clientConnection.readInput());
        } while (clientConnection.inputReaderIsReady());
        return rawRequest.toString();
    }

    private boolean requestIsValid(String rawRequest) {
        return rawRequest.length() > 1;
    }
}
