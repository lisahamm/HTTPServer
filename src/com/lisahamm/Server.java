package com.lisahamm;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private int portNumber;
    private boolean running = true;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private Router router;
    private Logger logger;

    public Server(int portNumber, Router router, Logger logger) {
        this.portNumber = portNumber;
        this.threadPool = Executors.newFixedThreadPool(4);
        this.router = router;
        this.logger = logger;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);

            System.out.println("Server is listening on port: " + portNumber);
            while (running) {
                threadPool.execute(new HttpTransaction(new ClientConnection(serverSocket.accept()),
                        new RequestParser(), new ResponseBuilder(), router, logger));
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            try {
                threadPool.shutdown();
                serverSocket.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
