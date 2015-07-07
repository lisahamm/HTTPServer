package com.lisahamm;

import com.lisahamm.application.router.AppRouterFactory;
import com.lisahamm.core.Server;

import java.io.*;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        startServer(args);
    }

    public static void startServer(String[] args) throws IOException {
        int portNumber;
        if (args.length != 1) {
            portNumber = 5000;
        } else {
            portNumber = Integer.parseInt(args[0]);
        }

        Server server = new Server(portNumber, new AppRouterFactory());
        server.run();
    }
}
