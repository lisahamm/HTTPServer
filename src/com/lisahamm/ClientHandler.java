package com.lisahamm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHandler extends Thread {
    private BufferedReader in;
    private DataOutputStream out;
    private Router router;

    public ClientHandler(BufferedReader in, DataOutputStream out, Router router) {
        this.in = in;
        this.out = out;
        this.router = router;
    }

    public void run() {
        try {
            StringBuilder rawRequest = new StringBuilder();

            do {
                rawRequest.append((char) in.read());
            } while (in.ready());

            if (rawRequest.length() > 1) {
                RequestParser parser = new RequestParser();
                HTTPRequest request = parser.generateParsedRequest(rawRequest.toString());
                ResponseBuilder responseBuilder = new ResponseBuilder();

                router.invoke(request, responseBuilder);

                String response = responseBuilder.getResponseHeader();
                byte[] body = responseBuilder.getBody();
                if(response.length() < 2) {
                    response = "HTTP/1.1 404 Not Found\r\n";
                    body = "".getBytes();
                }

                out.flush();
                out.writeBytes(response + "\r\n");
                out.flush();

                if (body != null) {
                    out.write(body);
                    out.flush();
                }

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
