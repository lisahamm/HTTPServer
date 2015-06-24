package com.lisahamm;

import java.io.IOException;
import java.net.Socket;

public interface ConnectionIO {
    public Socket getSocket();

    public void openInputReader() throws IOException;

    public void openOutputStream() throws IOException;

    public int readInput() throws IOException;

    public boolean inputReaderIsReady() throws IOException;

    public void writeToOutputStream(String output) throws IOException;

    public void writeToOutputStream(byte[] output) throws IOException;

    public void close() throws IOException;

    public boolean isValid() throws IOException;

}
