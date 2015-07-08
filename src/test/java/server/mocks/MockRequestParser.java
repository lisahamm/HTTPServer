package server.mocks;

import server.core.requests.Parser;
import server.core.requests.Request;

public class MockRequestParser implements Parser {
    public boolean isParsedRequestGenerated = false;

    public Request generateParsedRequest(String rawRequest) {
        isParsedRequestGenerated = true;
        return new MockHTTPRequest();
    }
}
