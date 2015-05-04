package com.lisahamm;

import java.net.*;
import java.io.*;
import java.util.Date;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        int portNumber;
        if (args.length != 1) {
            portNumber = 5000;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }

        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Server is listening on port: " + portNumber);
        boolean running = true;

        try {
            while (running) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("Connection made with " + clientSocket);

                String httpRequest = in.readLine();
                System.out.println(httpRequest);

                RequestHandler requestHandler = new RequestHandler(new RequestParser(httpRequest));

                String response = requestHandler.getResponse();

                out.flush();
                out.write(response);
                out.flush();
                in.close();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        } finally {
            serverSocket.close();
        }
    }
}
