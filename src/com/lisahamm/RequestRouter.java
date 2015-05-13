package com.lisahamm;

import java.util.*;

public class RequestRouter {
    private static Map<String, RequestHandler> routes = new HashMap<>();
    private static List<RequestHandler> requestHandlers = new LinkedList<>();

    public void route(HTTPRequest request, ResponseBuilder response) {
        requestHandlers.add(new Handler());
        System.out.println("Number of handlers: " + requestHandlers.size());
        boolean handled = false;
        while (handled == false) {
            for (RequestHandler requestHandler : requestHandlers) {
                handled = requestHandler.handle(request, response);
            }
        }
    }
}

//import java.util.Map;
//        import java.util.HashMap;
//
//public class Router {
//
//    static class Route {
//        private String method;
//        private String uri;
//        private int handler;
//
//        public Route(String method, String uri, int handler) {
//            this.method = method;
//            this.uri = uri;
//            this.handler = handler;
//        }
//
//        public String toString() {
//            return this.method + this.uri;
//        }
//    }
//
//    private static Map<String, Route> routes;
//
//    public static void main(String[] args) {
//        routes = new HashMap<>();
//
//        Router.get("/foo", 10);
//        Router.post("/foo", 20);
//
//        System.out.println(
//                routes.toString()
//        );
//    }
//
//    public static void get(String path, int i) {
//        routes.put("GET:" + path, new Route("GET", path, i));
//    }
//
//    public static void post(String path, int i) {
//        routes.put("POST:" + path, new Route("POST", path, i));
//    }
//}