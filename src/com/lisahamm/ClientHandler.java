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

                sendResponse(responseBuilder);
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

    private void sendResponse(ResponseBuilder response) throws IOException {
        String responseHeader = response.getResponseHeader();
        byte[] body = response.getBody();
        if(responseHeader.length() < 2) {
            responseHeader = "HTTP/1.1 404 Not Found\r\n";
            body = "".getBytes();
        }

        out.flush();
        out.writeBytes(responseHeader + "\r\n");
        out.flush();

        if (body != null) {
            out.write(body);
            out.flush();
        }

        in.close();
        out.close();
    }
}
