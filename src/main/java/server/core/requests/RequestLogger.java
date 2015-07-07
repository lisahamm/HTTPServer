package server.core.requests;


import server.core.managers.ResourceManager;

public class RequestLogger implements Logger {
    private static ResourceManager resourceManager;

    public RequestLogger(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void addEntry(String request) {
        resourceManager.appendToResource("/logs", request);
    }
}
