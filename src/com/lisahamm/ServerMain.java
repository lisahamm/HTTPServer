package com.lisahamm;

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

        FileManager fileManager = new AppFileManager();
        Logger logger = new RequestLogger(fileManager);
        Router router = buildRouter(fileManager);
        Server server = new Server(portNumber, router, logger);
        server.run();
    }

    private static Router buildRouter(FileManager fileManager) {
        String pathToPublicDirectory = "./../cob_spec/public";
        String pathToDataStorage = "./../cob_spec/database";
        ResourceManager resourceManager = new AppResourceManager(fileManager, pathToPublicDirectory, pathToDataStorage);
        return new CobSpecRouter(resourceManager);
    }
}
