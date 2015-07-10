package server.application.router;

import server.core.Constants.HttpStatus;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.router.Router;
import server.core.router.Routes;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CobSpecRouter implements Router {
    private Routes routes;
    private Map<String, Map<String, RouteHandler>> appRoutes;
    private Set<String> resources = new HashSet<>();

    public CobSpecRouter(Routes routes) {
        this.routes = routes;
    }

    public void invoke(Request request, ResponseBuilder response) {
        generateRoutes();
        setResources();
        RouteHandler routeHandler = findHandler(request.getRequestMethod(), request.getRequestURI());
        if (routeHandler != null) {
            routeHandler.invoke(request, response);
        } else {
            evaluateClientError(request, response);
        }
    }

    private RouteHandler findHandler(String verb, String uri) {
        if (appRoutes.containsKey(verb)) {
            return appRoutes.get(verb).get(uri);
        } else {
            return null;
        }
    }

    private void evaluateClientError(Request request, ResponseBuilder response) {
        if (resources.contains(request.getRequestURI())) {
            response.addStatusLine(HttpStatus.CODE405.get());
        } else if (appRoutes.containsKey(request.getRequestMethod())) {
            response.addStatusLine(HttpStatus.CODE404.get());
        } else {
            response.addStatusLine(HttpStatus.CODE400.get());
        }
    }

    private void generateRoutes() {
        appRoutes = routes.generate();
    }

    private void setResources() {
        resources = routes.getResources();
    }

}
