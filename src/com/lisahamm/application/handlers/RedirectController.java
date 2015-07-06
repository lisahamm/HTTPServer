package com.lisahamm.application.handlers;


import com.lisahamm.Request;
import com.lisahamm.ResponseBuilder;

public class RedirectController extends BaseController {

    public RedirectController() {
        this.uri = "/redirect";
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(code302);
        response.addHeader("Location: http://localhost:5000/");
    }
}
