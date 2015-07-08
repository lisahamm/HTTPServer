package server;

import server.core.Server;
import server.application.router.AppRouterFactory;
import server.core.configuration.Settings;

import java.io.*;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        Settings.configureFromCommandLine(args);
        startServer();
    }

    private static void startServer() throws IOException {
        Server server = new Server(Settings.portNumber, new AppRouterFactory());
        server.run();
    }
}
