package com.lisahamm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHandler extends Thread {
    private ClientConnection clientConnection;
    private Router router;

    public ClientHandler(ClientConnection clientConnection, Router router) {
        this.clientConnection = clientConnection;
        this.router = router;
    }

    public void run() {
        try {
            clientConnection.openInputReader();
            clientConnection.openOutputStream();

            StringBuilder rawRequest = new StringBuilder();

            do {
                rawRequest.append((char) clientConnection.readInput());
            } while (clientConnection.inputReaderIsReady());

            if (rawRequest.length() > 1) {

                RequestParser parser = new RequestParser();
                HTTPRequest request = parser.generateParsedRequest(rawRequest.toString());
                ResponseBuilder responseBuilder = new ResponseBuilder();

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
}
