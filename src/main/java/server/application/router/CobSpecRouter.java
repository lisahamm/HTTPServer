package server.application.router;

import server.application.controllers.*;
import server.core.Constants.Response;
import server.core.managers.ResourceManager;
import server.core.requests.Logger;
import server.core.requests.Request;
import server.core.requests.RequestLogger;
import server.core.response.ResponseBuilder;
import server.core.router.Router;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CobSpecRouter implements Router {
    protected ResourceManager resourceManager;
    private Map<String, Map<String, Controller>> routes = new HashMap<>();
    private Set<String> resources = new HashSet<>();

    public CobSpecRouter(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void invoke(Request request, ResponseBuilder response) {
        generateControllers();
        Controller controller = findExecutable(request.getRequestMethod(), request.getRequestURI());
        if (controller != null) {
            controller.execute(request, response);
        } else {
            evaluateClientError(request, response);
        }
    }

    private Controller findExecutable(String verb, String uri) {
        if (routes.containsKey(verb)) {
            return routes.get(verb).get(uri);
        } else {
            return null;
        }
    }

    private void evaluateClientError(Request request, ResponseBuilder response) {
        if (resources.contains(request.getRequestURI())) {
            response.addStatusLine(Response.code405);
        } else if (routes.containsKey(request.getRequestMethod())) {
            response.addStatusLine(Response.code404);
        } else {
            response.addStatusLine(Response.code400);
        }
    }

    private void generateControllers() {
        configureMethods();

        registerRoute("GET", "/", new RootController(resourceManager));

        registerRoute("GET", "/form", new FormController(resourceManager));
        registerRoute("POST", "/form", new FormController(resourceManager));
        registerRoute("PUT", "/form", new FormController(resourceManager));
        registerRoute("DELETE", "/form", new FormController(resourceManager));

        registerRoute("GET", "/method_options", new MethodOptionsController(resourceManager));
        registerRoute("POST", "/method_options", new MethodOptionsController(resourceManager));
        registerRoute("PUT", "/method_options", new MethodOptionsController(resourceManager));
        registerRoute("OPTIONS", "/method_options", new MethodOptionsController(resourceManager));
        registerRoute("HEAD", "/method_options", new MethodOptionsController(resourceManager));

        registerRoute("GET", "/method_options", new MethodOptionsController(resourceManager));

        registerRoute("GET", "/file1", new StaticFileController(resourceManager));
        registerRoute("GET", "/file2", new StaticFileController(resourceManager));
        registerRoute("GET", "/image.gif", new StaticFileController(resourceManager));
        registerRoute("GET", "/image.jpeg", new StaticFileController(resourceManager));
        registerRoute("GET", "/image.png", new StaticFileController(resourceManager));
        registerRoute("GET", "/partial_content.txt", new StaticFileController(resourceManager));
        registerRoute("GET", "/patch-content.txt", new StaticFileController(resourceManager));
        registerRoute("PATCH", "/patch-content.txt", new StaticFileController(resourceManager));
        registerRoute("GET", "/text-file.txt", new StaticFileController(resourceManager));

        registerRoute("GET", "/redirect", new RedirectController(resourceManager));

        registerRoute("GET", "/parameters", new ParametersController(resourceManager));

        registerRoute("GET", "/logs", new LogsController(resourceManager));
    }

    private void configureMethods() {
        routes.put("GET", new HashMap<>());
        routes.put("POST", new HashMap<>());
        routes.put("PUT", new HashMap<>());
        routes.put("PATCH", new HashMap<>());
        routes.put("DELETE", new HashMap<>());
        routes.put("OPTIONS", new HashMap<>());
        routes.put("HEAD", new HashMap<>());
    }

    private void registerRoute(String verb, String uri, Controller controller) {
        resources.add(uri);
        routes.get(verb).put(uri, controller);
    }

}
