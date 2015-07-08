package server.mocks;

import server.core.connections.ConnectionIO;

import java.io.IOException;
import java.net.Socket;

public class MockClientConnection implements ConnectionIO {
    public boolean isInputReaderOpened = false;
    public boolean isOutputStreamOpened = false;
    public boolean isResponseSent = false;
    public boolean isClosed = false;

    public Socket getSocket() {
        return null;
    }

    public void openInputReader() throws IOException {
        isInputReaderOpened = true;
    }

    public void openOutputStream() throws IOException {
        isOutputStreamOpened = true;
    }

    public String readInputToString() throws IOException {
        return "GET /form HTTP/1.1\r\n";
    }

    public boolean inputReaderIsReady() throws IOException {
        return true;
    }

    public void writeToOutputStream(byte[] output) throws IOException {
        isResponseSent = true;
    }

    public void close() throws IOException {
        isClosed = true;
    }

    public boolean isValid() throws IOException {
        return true;
    }
}