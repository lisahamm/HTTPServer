package server.core.requests;


public class HeaderParser {

    public static String parseRange(Request request) {
        return request.getHeaders().get("Range").split("=") [1];
    }

    public static String parseAuthorizationHeader(Request request) {
        String credentials = request.getHeaders().get("Authorization");
        return credentials.split("Basic", 2)[1].trim();
    }
}
