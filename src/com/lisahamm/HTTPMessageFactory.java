package com.lisahamm;


public class HTTPMessageFactory {
    private RequestParser parser = new RequestParser();

    public HTTPRequest request(String rawRequest) {
            return parser.generateParsedRequest(rawRequest.toString());
        }

    public ResponseBuilder response() {
            return new ResponseBuilder();
        }

}
