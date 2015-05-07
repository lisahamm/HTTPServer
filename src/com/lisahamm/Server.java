package com.lisahamm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private int portNumber = 0;
    private boolean running = true;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is listening on port: " + portNumber);
            while (running) {
                clientSocket = serverSocket.accept();
                System.out.println("Connection made with " + clientSocket);
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                ClientHandler clientHandler = new ClientHandler(in, out);
                clientHandler.run();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            try {
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
