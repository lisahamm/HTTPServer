package com.lisahamm.application.controllers;

import com.lisahamm.core.requests.Request;
import com.lisahamm.core.managers.ResourceManager;
import com.lisahamm.core.response.ResponseBuilder;
import com.lisahamm.core.helpers.HtmlBuilder;

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
        response.addStatusLine(code200);
        response.addHeader(contentTypeHTML);
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
