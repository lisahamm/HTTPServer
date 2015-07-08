package server.core;

import server.core.connections.ConnectionIO;
import server.core.requests.*;
import server.core.response.ResponseBuilder;
import server.core.router.Router;

import java.io.IOException;

public class HttpTransaction implements Runnable {
    private ConnectionIO clientConnection;
    private Parser parser;
    private ResponseBuilder responseBuilder;
    private Router router;
    private Logger logger;

    public HttpTransaction(ConnectionIO clientConnection, Parser parser,
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

            if (isRequestValid(rawRequest)) {
                logger.addEntry(rawRequest);
                Request request = parser.generateParsedRequest(rawRequest);
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
        return clientConnection.readInputToString();
    }

    private boolean isRequestValid(String rawRequest) {
        return rawRequest.length() > 1;
    }
}
