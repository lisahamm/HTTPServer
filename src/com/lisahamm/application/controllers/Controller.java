package com.lisahamm.application.controllers;

import com.lisahamm.core.requests.Request;
import com.lisahamm.core.response.ResponseBuilder;

public interface Controller {
    boolean shouldExecute(Request request);
    void execute(Request request, ResponseBuilder response);
}
