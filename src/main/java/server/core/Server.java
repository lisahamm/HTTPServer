package server.core;

import server.core.connections.ClientConnection;
import server.core.requests.RequestParser;
import server.core.response.ResponseBuilder;
import server.core.router.RouterFactory;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private int portNumber;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private RouterFactory routerFactory;

    public Server(int portNumber, RouterFactory routerFactory) {
        this.portNumber = portNumber;
        this.threadPool = Executors.newFixedThreadPool(4);
        this.routerFactory = routerFactory;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);

            System.out.println("Server is listening on port: " + portNumber);

            while (!threadPool.isShutdown()) {
                threadPool.execute(new HttpTransaction(new ClientConnection(serverSocket.accept()),
                        new RequestParser(), new ResponseBuilder(), routerFactory.buildRouter()));
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
