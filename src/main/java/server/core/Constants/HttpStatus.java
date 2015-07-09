package server.core.Constants;

public enum HttpStatus {
    CODE200("200 OK"),
    CODE204("204 No Content"),
    CODE206("206 Partial Content"),
    CODE302("302 Found"),
    CODE400("400 Bad Request"),
    CODE401("401 Unauthorized"),
    CODE404("404 Not Found"),
    CODE405("405 Method Not Allowed"),
    CODE412("412 Precondition Failed");

    private final String status;

    HttpStatus(String status) {
        this.status = status;
    }

    public static String HTTP_VERSION = "HTTP/1.1";
    public static final String CRLF = "\r\n";

    public String get() {
        return HTTP_VERSION + " " + status + CRLF;
    }
}