package com.lisahamm;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements ConnectionIO {
    private Socket clientSocket;
    private DataOutputStream outputStream;
    private BufferedReader inputReader;

    public ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getSocket() {
        return clientSocket;
    }

    public void openInputReader() throws IOException {
        inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void openOutputStream() throws IOException {
        outputStream = new DataOutputStream(clientSocket.getOutputStream());
    }

    public int readInput() throws IOException {
        return inputReader.read();
    }

    public boolean inputReaderIsReady() throws IOException {
        return inputReader.ready();
    }

    public void writeToOutputStream(String output) throws IOException {
        outputStream.flush();
        outputStream.writeBytes(output);
        outputStream.flush();
    }

    public void writeToOutputStream(byte[] output) throws IOException {
        outputStream.flush();
        outputStream.write(output);
        outputStream.flush();
    }

    public void close() throws IOException {
        clientSocket.shutdownInput();
        clientSocket.shutdownOutput();
        clientSocket.close();
    }

    public boolean isValid() {
        return (!clientSocket.isOutputShutdown()) && clientSocket.isConnected();
    }
}