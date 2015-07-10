package server.application.router;

import server.core.router.Router;
import server.core.router.RouterFactory;
import server.core.router.Routes;

public class AppRouterFactory implements RouterFactory {
    Routes routes;

    public AppRouterFactory() {
        this.routes = new CobSpecRoutes();
    }
    public Router buildRouter() {
        return new CobSpecRouter(routes);
    }
}
