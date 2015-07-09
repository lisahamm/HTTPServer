package server.core.Constants;

public enum HttpHeader {
    CONTENT_TYPE_HTML("Content-Type: text/html"),
    CONTENT_TYPE_PLAIN("Content-Type: text/plain");

    private final String value;

    HttpHeader(String value) {
        this.value = value;
    }

    public static final String CRLF = "\r\n";

    public String get() {
        return value + CRLF;
    }
}
