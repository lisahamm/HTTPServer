package server.application.router;

import server.application.controllers.*;
import server.core.managers.AppFileManager;
import server.core.managers.AppResourceManager;
import server.core.managers.ResourceManager;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;
import server.core.router.AppRoutes;

public class CobSpecRoutes extends AppRoutes {
    private ResourceManager resourceManager;

    public CobSpecRoutes() {
        this.resourceManager = new AppResourceManager(new AppFileManager());
    }

    @Override
    protected void build() {
        get("/", (Request request, ResponseBuilder response) ->
            new RootController(resourceManager).handleGet(request, response));

        get("/form", (Request request, ResponseBuilder response) ->
                new FormController(resourceManager).handleGet(request, response));

        post("/form", (Request request, ResponseBuilder response) ->
                new FormController(resourceManager).handlePost(request, response));

        put("/form", (Request request, ResponseBuilder response) ->
                new FormController(resourceManager).handlePut(request, response));

        delete("/form", (Request request, ResponseBuilder response) ->
                new FormController(resourceManager).handlePost(request, response));

        get("/method_options", (Request request, ResponseBuilder response) ->
                new MethodOptionsController(resourceManager).handleGet(request, response));

        post("/method_options", (Request request, ResponseBuilder response) ->
                new MethodOptionsController(resourceManager).handlePost(request, response));

        put("/method_options", (Request request, ResponseBuilder response) ->
                new MethodOptionsController(resourceManager).handlePut(request, response));

        options("/method_options", (Request request, ResponseBuilder response) ->
                new MethodOptionsController(resourceManager).handleOptions(request, response));

        head("/method_options", (Request request, ResponseBuilder response) ->
                new MethodOptionsController(resourceManager).handleHead(request, response));

        get("/file1", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/file2", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/image.gif", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/image.jpeg", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/image.png", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/partial_content.txt", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/patch-content.txt", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        patch("/partial_content.txt", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handlePatch(request, response));

        get("/text-file.txt", (Request request, ResponseBuilder response) ->
                new StaticFileController(resourceManager).handleGet(request, response));

        get("/redirect", (Request request, ResponseBuilder response) ->
                new RedirectController(resourceManager).handleGet(request, response));

        get("/parameters", (Request request, ResponseBuilder response) ->
                new RedirectController(resourceManager).handleGet(request, response));

        get("/logs", (Request request, ResponseBuilder response) ->
                new LogsController(resourceManager).handleGet(request, response));
    }
}
