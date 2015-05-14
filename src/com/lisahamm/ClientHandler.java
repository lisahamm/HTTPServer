package com.lisahamm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ClientHandler extends Thread {
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        try {
            String rawRequest = in.readLine();
            if (rawRequest != null) {
                RequestParser parser = new RequestParser();
                HTTPRequest request = parser.generateParsedRequest(rawRequest);
                ResponseBuilder responseBuilder = new ResponseBuilder();

                RequestRouter router = new RequestRouter();
                router.route(request, responseBuilder);

                String response = responseBuilder.getResponse();
                if(response.length() < 2) {
                    response = "HTTP/1.1 404 Not Found";
                }
                out.flush();
                out.write(response);
                out.flush();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
