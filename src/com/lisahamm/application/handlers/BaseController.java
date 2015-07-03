package com.lisahamm.application.handlers;

import com.lisahamm.Request;

public class BaseController {
    private String uri;

    public BaseController(String uri) {
        this.uri = uri;
    }

    public boolean itShouldExecute(Request request) {
        return uri.equals(request.getRequestURI());
    }
}
