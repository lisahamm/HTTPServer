package com.lisahamm;

import java.io.IOException;

public class ClientRequestHandler extends Thread {
    private ClientConnection clientConnection;
    private HTTPMessageFactory messageFactory;
    private Router router;
    private String crlf = "\r\n";

    public ClientRequestHandler(ClientConnection clientConnection, HTTPMessageFactory messageFactory, Router router) {
        this.clientConnection = clientConnection;
        this.messageFactory = messageFactory;
        this.router = router;
    }

    public void run() {
        try {
            clientConnection.openInputReader();
            clientConnection.openOutputStream();

            String rawRequest = readRawRequest();

            if (rawRequest.length() > 5) {
                HTTPRequest request = messageFactory.request(rawRequest);
                ResponseBuilder responseBuilder = messageFactory.response();

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

    private String readRawRequest() throws IOException {
        StringBuilder rawRequest = new StringBuilder();

        do {
            rawRequest.append((char) clientConnection.readInput());
        } while (clientConnection.inputReaderIsReady());

        return rawRequest.toString();
    }

    private void sendResponse(ResponseBuilder responseBuilder) throws IOException {
        byte[] body = responseBuilder.getBody();

        clientConnection.writeToOutputStream(responseBuilder.getResponseHeader());

        if (body != null) {
            clientConnection.writeToOutputStream(crlf);
            clientConnection.writeToOutputStream(body);
        }
    }
}
