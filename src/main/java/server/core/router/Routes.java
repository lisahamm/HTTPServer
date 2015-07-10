package server.core.router;

import server.application.router.RouteHandler;

import java.util.Map;
import java.util.Set;

public interface Routes {
    Map<String, Map<String, RouteHandler>> generate();
    Set<String> getResources();
}
