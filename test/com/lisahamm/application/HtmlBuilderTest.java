package com.lisahamm.application;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HtmlBuilderTest {
    private HtmlBuilder viewBuilder;

    @Before
    public void setUp() throws Exception {
        viewBuilder = new HtmlBuilder();

    }

    @Test
    public void testAddDocType() throws Exception {
        viewBuilder.addDocType();
        assertEquals("<!DOCTYPE html>\n", viewBuilder.getView());
    }

    @Test
    public void testAddHtmlOpeningTag() throws Exception {
        viewBuilder.addOpeningHtmlTag();
        assertTrue(viewBuilder.getView().contains("<html>\n"));
    }

    @Test
    public void testAddHtmlClosingTag() throws Exception {
        viewBuilder.addClosingHtmlTag();
        assertTrue(viewBuilder.getView().contains("</html>"));
    }

    @Test
    public void testAddBodyOpeningTag() throws Exception {
        viewBuilder.addOpeningBodyTag();
        assertTrue(viewBuilder.getView().contains("<body>\n"));
    }

    @Test
    public void testAddBodyClosingTag() throws Exception {
        viewBuilder.addClosingBodyTag();
        assertTrue(viewBuilder.getView().contains("</body>\n"));
    }

    @Test
    public void testAddHeader() throws Exception {
        String header = "Header Test";

        viewBuilder.addHeader("h1", header);

        assertTrue(viewBuilder.getView().contains("<h1>" + header + "</h1>"));
    }

    @Test
    public void testAddImage() throws Exception {
        String imageFileName = "image.png";

        viewBuilder.addImage(imageFileName);

        assertTrue(viewBuilder.getView().contains("<img src=\"" + imageFileName + "\">"));
    }

    @Test
    public void testAddLink() throws Exception {
        String uri = "/file1";
        String displayText = "File 1";

        viewBuilder.addLink(uri, displayText);

        String view = viewBuilder.getView();
        assertTrue(view.contains("<a href=\"" + uri + "\">" + displayText + "</a>"));
    }
}