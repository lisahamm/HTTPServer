package com.lisahamm.application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlBuilderTest {
    private HtmlBuilder viewBuilder;
    private StringBuilder html;


    @Before
    public void setUp() throws Exception {
        viewBuilder = new HtmlBuilder();
        html = new StringBuilder();
    }

    @Test
    public void testAddDocType() throws Exception {
        viewBuilder.addDocType(html);
        assertEquals("<!DOCTYPE html>\n", html.toString());
    }

    @Test
    public void testAddHtmlOpeningTag() throws Exception {
        viewBuilder.addOpeningHtmlTag(html);
        assertTrue(html.toString().contains("<html>\n"));
    }

    @Test
    public void testAddHtmlClosingTag() throws Exception {
        viewBuilder.addClosingHtmlTag(html);
        assertTrue(html.toString().contains("</html>"));
    }

    @Test
    public void testAddBodyOpeningTag() throws Exception {
        viewBuilder.addOpeningBodyTag(html);
        assertTrue(html.toString().contains("<body>\n"));
    }

    @Test
    public void testAddBodyClosingTag() throws Exception {
        viewBuilder.addClosingBodyTag(html);
        assertTrue(html.toString().contains("</body>\n"));
    }

    @Test
    public void testAddHeader() throws Exception {
        String header = "Header Test";

        viewBuilder.addHeader(html, "h1", header);

        assertTrue(html.toString().contains("<h1>" + header + "</h1>"));
    }

    @Test
    public void testAddImage() throws Exception {
        String imageUri = "/image.png";

        viewBuilder.addImage(html, imageUri);

        assertTrue(html.toString().contains("<img src=\"" + imageUri + "\">"));
    }

    @Test
    public void testAddLink() throws Exception {
        String uri = "/file1";
        String displayText = "File 1";

        viewBuilder.addLink(html, uri, displayText);

        String view = html.toString();
        assertTrue(view.contains("<a href=\"" + uri + "\">" + displayText + "</a>"));
    }

    @Test
    public void testAddNewLine() throws Exception {
        viewBuilder.addNewLine(html);

        assertTrue(html.toString().contains("\n"));
    }

    @Test
    public void testAddToTemplate() throws Exception {
        viewBuilder.addHeader(html, "h1", "Header");
        viewBuilder.addToTemplate(html);
        String expectedView = "<!DOCTYPE html>\n<html>\n<body>\n<h1>Header</h1>\n</body>\n</html>";

        assertEquals(expectedView, html.toString());
    }
}