package com.lisahamm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;

public class Server implements Runnable {
    private int portNumber = 0;
    private boolean running = true;
    private ServerSocket serverSocket;
    private Router router;
    private Logger logger;

    public Server(int portNumber, Router router, Logger logger) {
        this.portNumber = portNumber;
        this.router = router;
        this.logger = logger;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);

            System.out.println("Server is listening on port: " + portNumber);
            while (running) {
                ClientConnection clientConnection = new ClientConnection(serverSocket.accept());
                System.out.println("Connection made with " + clientConnection.getSocket());
                
                HttpTransaction httpTransaction = new HttpTransaction(clientConnection,
                        new RequestParser(), new ResponseBuilder(), router, logger);
                httpTransaction.start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
