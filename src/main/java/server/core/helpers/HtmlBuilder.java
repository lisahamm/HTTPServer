package server.core.helpers;

public class HtmlBuilder {
    private StringBuilder view;
    private String docType = "<!DOCTYPE html>";
    private String openingHtmlTag = "<html>";
    private String closingHtmlTag = "</html>";
    private String openingBodyTag = "<body>";
    private String closingBodyTag = "</body>";
    private String newLine = "\n";
    private String templateStart = "<!DOCTYPE html>\n<html>\n<body>\n";
    private String templateEnd = "</body>\n</html>";

    public HtmlBuilder() {
        this.view = new StringBuilder();
    }

    public void addDocType(StringBuilder html) {
        html.append(docType + newLine);
    }

    public void addOpeningHtmlTag(StringBuilder html) {
        html.append(openingHtmlTag + newLine);
    }

    public void addClosingHtmlTag(StringBuilder html) {
        html.append(closingHtmlTag);
    }

    public void addOpeningBodyTag(StringBuilder html) {
        html.append(openingBodyTag + newLine);
    }

    public void addClosingBodyTag(StringBuilder html) {
        html.append(closingBodyTag + newLine);
    }

    public void addHeader(StringBuilder html, String headerType, String data) {
        switch (headerType) {
            case "h1":
                html.append("<h1>" + data + "</h1>" + newLine);
                break;
            default:
                html.append("<h1>" + data + "</h1>" + newLine);
                break;
        }
    }

    public void addImage(StringBuilder html, String fileName) {
        html.append("<img src=\"" + fileName + "\">");
    }

    public void addLink(StringBuilder html, String uri, String displayText) {
        html.append("<a href=\"" + uri + "\">" + displayText + "</a>");
    }

    public void addNewLine(StringBuilder html) {
        html.append(newLine);
    }

    public void addToTemplate(StringBuilder html) {
        html.insert(0, templateStart);
        html.append(templateEnd);
    }


}
