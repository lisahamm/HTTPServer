package com.lisahamm;

import java.net.*;
import java.io.*;

public class ServerMain {
    private static int portNumber;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static String response;

    public static void main(String[] args) throws Exception {
        startServer(args);
        boolean running = true;
        while (running) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Connection made with " + clientSocket);
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String rawRequest = in.readLine();
                if (rawRequest != null) {
                    RequestParser parser = new RequestParser();
                    HTTPRequest request = parser.generateParsedRequest(rawRequest);
                    RequestHandlerFactory handlerFactory = new RequestHandlerFactory();
                    try {
                        RequestHandler requestHandler = handlerFactory.make(request.getRequestMethod());
                        requestHandler.handle(request);
                        response = requestHandler.getResponse();
                    } catch (Exception e) {
                        response = "HTTP/1.1 404 Not Found";
                    }
                    out.flush();
                    out.write(response);
                    out.flush();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                        + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
            } finally {
                clientSocket.close();
            }
        }
    }

    public static void startServer(String[] args) throws IOException {
        if (args.length != 1) {
            portNumber = 5000;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }
        serverSocket = new ServerSocket(portNumber);
        System.out.println("Server is listening on port: " + portNumber);
    }
}
