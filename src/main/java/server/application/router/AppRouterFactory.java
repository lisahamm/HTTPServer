package server.application.router;

import server.core.managers.AppFileManager;
import server.core.managers.AppResourceManager;
import server.core.router.Router;
import server.core.router.RouterFactory;

public class AppRouterFactory implements RouterFactory {
    public Router buildRouter() {
        return new CobSpecRouter(new AppResourceManager(new AppFileManager()));
    }
}
