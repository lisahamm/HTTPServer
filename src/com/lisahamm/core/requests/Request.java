package com.lisahamm.core.requests;

import java.util.Map;

public interface Request {
    String getRequestMethod();

    String getRequestURI();

    String getHTTPVersion();

    Map<String, String> getHeaders();

    String getBody();

    Map<String, String> getParams();
}
