package server.core.router;

import server.application.router.RouteHandler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AppRoutes implements Routes {
    protected Map<String, Map<String, RouteHandler>> routes = new HashMap<>();
    protected Set<String> resources = new HashSet<>();
    private String httpGET = "GET";
    private String httpPOST = "POST";
    private String httpPUT = "PUT";
    private String httpDELETE = "DELETE";
    private String httpPATCH = "PATCH";
    private String httpOPTIONS = "OPTIONS";
    private String httpHEAD = "HEAD";

    public AppRoutes() {
    }

    public Map<String, Map<String, RouteHandler>> generate() {
        configureMethods();
        build();
        return routes;
    }

    public Set<String> getResources() {
        return resources;
    }

    protected abstract void build();

    protected void get(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpGET).put(uri, routeHandler);
    }

    protected void post(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpPOST).put(uri, routeHandler);
    }

    protected void put(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpPUT).put(uri, routeHandler);
    }

    protected void delete(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpDELETE).put(uri, routeHandler);
    }

    protected void patch(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpPATCH).put(uri, routeHandler);
    }

    protected void options(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpOPTIONS).put(uri, routeHandler);
    }

    protected void head(String uri, RouteHandler routeHandler) {
        resources.add(uri);
        routes.get(httpHEAD).put(uri, routeHandler);
    }

    private void configureMethods() {
        routes.put(httpGET, new HashMap<>());
        routes.put(httpPOST, new HashMap<>());
        routes.put(httpPUT, new HashMap<>());
        routes.put(httpDELETE, new HashMap<>());
        routes.put(httpPATCH, new HashMap<>());
        routes.put(httpOPTIONS, new HashMap<>());
        routes.put(httpHEAD, new HashMap<>());
    }
}
