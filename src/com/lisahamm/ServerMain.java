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
        
        try (
             ServerSocket serverSocket = new ServerSocket(portNumber)
        ) {
            System.out.println("Server is listening on port: " + portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                System.out.println("Connection made with " + clientSocket);

                String inputLine = in.readLine();
                System.out.println(inputLine);

                if (inputLine.contains("GET /")) {
                    out.flush();
                    out.write("HTTP/1.1 200 OK\r\n");
                    out.write("Content-Type: text/plain\r\n");
                    out.write("Date: " + new Date() + "\r\n");
                    out.write("\r\n");
                    out.flush();
                } else if (inputLine.contains("POST /")) {
                    out.flush();
                    out.write("HTTP/1.1 200 OK\r\n");
                    out.write("Content-Type: text/plain\r\n");
                    out.write("Date: " + new Date() + "\r\n");
                    out.write("\r\n");
                    out.flush();
                } else {
                    out.flush();
                    out.write("HTTP/1.1 404 Not Found\r\n");
                    out.flush();
                }
                in.close();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
