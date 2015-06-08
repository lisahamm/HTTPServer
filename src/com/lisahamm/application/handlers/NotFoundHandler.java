package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.RequestHandler;
import com.lisahamm.ResponseBuilder;

public class NotFoundHandler implements RequestHandler {
    public boolean handle(Request request, ResponseBuilder response) {
        response.addStatusLine("404");
        return true;
    }
}
