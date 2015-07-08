package server.application.controllers;

import server.core.Constants.Response;
import server.core.helpers.HtmlBuilder;
import server.core.managers.ResourceManager;
import server.core.requests.Request;
import server.core.response.ResponseBuilder;

import java.util.List;

public class RootController extends BaseController {
    private HtmlBuilder viewBuilder;

    public RootController(ResourceManager resourceManager) {
        this.uri = "/";
        this.resourceManager = resourceManager;
        this.viewBuilder = new HtmlBuilder();
    }

    @Override
    protected void handleGet(Request request, ResponseBuilder response) {
        response.addStatusLine(Response.code200);
        response.addHeader(Response.contentTypeHTML);
        List<String> publicFiles = resourceManager.getPublicDirectoryContents();
        String view = addHtmlLinks(publicFiles);
        response.addBody(view.getBytes());
    }

    private String addHtmlLinks(List<String> fileNames) {
        StringBuilder html = new StringBuilder();
        for (String file : fileNames) {
            viewBuilder.addLink(html, "/" + file, file);
            viewBuilder.addNewLine(html);
        }
        return html.toString();
    }
}
