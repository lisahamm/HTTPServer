package server.core.requests;


public class HeaderParser {

    public static String parseRange(Request request) {
        return request.getHeaders().get("Range").split("=") [1];
    }
}
