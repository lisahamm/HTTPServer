package server.core.requests;

public interface Parser {
    public Request generateParsedRequest(String rawRequest);
}
