package com.lisahamm.application;

public class HtmlBuilder {
    private StringBuilder view;
    private String docType = "<!DOCTYPE html>";
    private String openingHtmlTag = "<html>";
    private String closingHtmlTag = "</html>";
    private String openingBodyTag = "<body>";
    private String closingBodyTag = "</body>";
    private String newLine = "\n";

    public HtmlBuilder() {
        this.view = new StringBuilder();
    }

    public void addDocType() {
        view.append(docType + newLine);
    }

    public void addOpeningHtmlTag() {
        view.append(openingHtmlTag + newLine);
    }

    public void addClosingHtmlTag() {
        view.append(closingHtmlTag);
    }

    public void addOpeningBodyTag() {
        view.append(openingBodyTag + newLine);
    }

    public void addClosingBodyTag() {
        view.append(closingBodyTag + newLine);
    }

    public void addHeader(String headerType, String data) {
        switch (headerType) {
            case "h1":
                view.append("<h1>" + data + "</h1>" + newLine);
                break;
            default:
                view.append("<h1>" + data + "</h1>" + newLine);
                break;
        }
    }

    public void addImage(String fileName) {
        view.append("<img src=\"" + fileName + "\">");
    }

    public void addLink(String uri, String displayText) {
        view.append("<a href=\"" + uri + "\">" + displayText + "</a>");
    }


    public String getView() {
        return view.toString();
    }
}
