package com.lisahamm;

public class RequestHandlerFactory {

    public RequestHandler make(String requestMethod) throws Exception {
        if (requestMethod.equals("GET"))
            return new GetRequestHandler();
        else if (requestMethod.equals("POST"))
            return new PostRequestHandler();
        else if (requestMethod.equals("PUT"))
            return new PutRequestHandler();
        else if (requestMethod.equals("OPTIONS"))
            return new OptionsRequestHandler();
//        else if(requestMethod.equals("HEAD"))
//            return new HeadRequestHandler();
        else
            throw new Exception("RequestHandlerFactory cannot create handler for " + requestMethod + " request");
    }
}
