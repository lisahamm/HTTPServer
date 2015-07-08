package server.mocks;

import server.core.requests.Logger;

public class MockLogger implements Logger {
    public boolean isEntryAdded = false;

    public void addEntry(String data) {
        isEntryAdded = true;
    }
}