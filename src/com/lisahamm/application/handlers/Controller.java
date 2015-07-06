package com.lisahamm.application.handlers;

import com.lisahamm.Request;
import com.lisahamm.ResponseBuilder;

public interface Controller {
    boolean shouldExecute(Request request);
    void execute(Request request, ResponseBuilder response);
}
