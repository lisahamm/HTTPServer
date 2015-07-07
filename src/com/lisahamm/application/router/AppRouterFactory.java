package com.lisahamm.application.router;

import com.lisahamm.core.managers.AppFileManager;
import com.lisahamm.core.managers.AppResourceManager;
import com.lisahamm.core.router.Router;
import com.lisahamm.core.router.RouterFactory;

public class AppRouterFactory implements RouterFactory {
    public Router buildRouter() {
        return new CobSpecRouter(new AppResourceManager(new AppFileManager()));
    }
}
