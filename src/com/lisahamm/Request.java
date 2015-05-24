package com.lisahamm;

import java.util.Map;

public interface Request {
    String getRequestMethod();

    String getRequestURI();

    String getHTTPVersion();

    String getHeaders();

    String getBody();

    Map<String, String> getParams();
}
