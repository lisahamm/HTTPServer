package server.core;

import server.core.connections.ConnectionIO;
import server.core.requests.HTTPRequest;
import server.core.requests.Logger;
import server.core.requests.RequestParser;
import server.core.response.ResponseBuilder;
import server.core.router.Router;

import java.io.IOException;

public class HttpTransaction implements Runnable {
    private ConnectionIO clientConnection;
    private RequestParser parser;
    private ResponseBuilder responseBuilder;
    private Router router;
    private Logger logger;

    public HttpTransaction(ConnectionIO clientConnection, RequestParser parser,
                           ResponseBuilder responseBuilder, Router router, Logger logger) {
        this.clientConnection = clientConnection;
        this.parser = parser;
        this.responseBuilder = responseBuilder;
        this.router = router;
        this.logger = logger;
    }

    public void run() {
        try {
            openClientConnectionIO();
            String rawRequest = readInRawRequest();

            if (requestIsValid(rawRequest)) {
                logger.addEntry(rawRequest);
                HTTPRequest request = parser.generateParsedRequest(rawRequest);
                router.invoke(request, responseBuilder);
                if (clientConnection.isValid()) {
                    sendResponse(responseBuilder);
                }
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
        clientConnection.writeToOutputStream(response.getEntireResponse());
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
