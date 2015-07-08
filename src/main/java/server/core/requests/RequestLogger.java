package server.core.requests;

import server.application.Resources;

public class RequestLogger implements Logger {

    public void addEntry(String request) {
        Resources.logs.add(request);
    }
}
