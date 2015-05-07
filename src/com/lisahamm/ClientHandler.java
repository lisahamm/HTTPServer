package com.lisahamm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ClientHandler implements Runnable {
    private BufferedReader in;
    private PrintWriter out;
    private String response;

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
