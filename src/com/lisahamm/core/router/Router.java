package com.lisahamm.core.router;

import com.lisahamm.core.response.ResponseBuilder;
import com.lisahamm.core.requests.Request;

public interface Router {
    void invoke(Request request, ResponseBuilder response);
}
