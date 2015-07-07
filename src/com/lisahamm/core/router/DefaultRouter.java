package com.lisahamm.core.router;

import com.lisahamm.core.managers.ResourceManager;
import com.lisahamm.core.response.ResponseBuilder;
import com.lisahamm.application.controllers.Controller;
import com.lisahamm.core.requests.Request;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DefaultRouter implements Router {
    protected ResourceManager resourceManager;
    private static Map<String, Map<String, Controller>> routes = new HashMap<>();
    private static Set<String> resources = new HashSet<>();

    public DefaultRouter(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        setup();
    }

    public void invoke(Request request, ResponseBuilder response) {
        Controller controller = findExecutable(request.getRequestMethod(), request.getRequestURI());
        if (controller != null) {
            controller.execute(request, response);
        } else {
            evaluateClientError(request.getRequestURI(), response);
        }
    }

    private void registerRoute(String verb, String uri, Controller controller) {
        resources.add(uri);
        routes.get(verb).put(uri, controller);
    }

    private void generateControllers() {
        // register routes for application here
    }

    private void setup() {
        routes.put("GET", new HashMap<>());
        routes.put("POST", new HashMap<>());
        routes.put("PUT", new HashMap<>());
        routes.put("PATCH", new HashMap<>());
        routes.put("DELETE", new HashMap<>());
        routes.put("OPTIONS", new HashMap<>());
    }

    private Controller findExecutable(String verb, String uri) {
        return routes.get(verb).get(uri);
    }

    private void evaluateClientError(String uri, ResponseBuilder response) {
        if (resources.contains(uri)) {
            response.addStatusLine("405");
        } else {
            response.addStatusLine("404");
        }
    }
}
