package com.lisahamm;

import java.io.*;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int portNumber;
    private boolean running = true;
    private ServerSocket serverSocket;
    private ClientConnection clientConnection;
    private Router router;

    public Server(int portNumber, Router router) {
        this.portNumber = portNumber;
        this.router = router;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is listening on port: " + portNumber);

            while (running) {
                clientConnection = new ClientConnection(serverSocket.accept());
                System.out.println("Connection made with " + clientConnection.socket());

                ClientRequestHandler clientRequestHandler = new ClientRequestHandler(clientConnection,
                        new HTTPMessageFactory(), router);

                clientRequestHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            try {
                shutdown();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void shutdown() throws IOException {
        clientConnection.close();
        serverSocket.close();
    }
}
