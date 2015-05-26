package com.lisahamm;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FileHandlerTest {
    private RequestHandler fileHandler;
    private ResponseBuilder response;

    @Before
    public void setUp() throws Exception {
        response = new ResponseBuilder();
        FileManager fileManager = new MockFileManager();
        fileHandler = new FileHandler(fileManager);
    }

    @Test
    public void testItHandlesRequestForFileInPublicDirectory() throws Exception {
        HTTPRequest validRequest = generateRequest("GET", "/file1");
        boolean isHandled = fileHandler.handle(validRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(response.getBody().length > 0);
        assertTrue(isHandled);
    }

    @Test
    public void testHandlesResponseForImageInPublicDirectory() throws Exception {
        HTTPRequest validRequest = generateRequest("GET", "/image.gif");
        boolean isHandled = fileHandler.handle(validRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: image/gif\r\n"));
        assertTrue(response.getBody().length > 0);
        assertTrue(isHandled);
    }

    @Test
    public void testItHandlesRequestForMethodNotAllowed() throws Exception {
        HTTPRequest invalidRequest = generateRequest("POST", "/file1");
        boolean isHandled = fileHandler.handle(invalidRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 405 Method Not Allowed"));
        assertTrue(isHandled);
    }

    @Test
    public void testDoesNotHandleRequestIfUriDoesNotExistInDirectory() throws Exception {
        HTTPRequest invalidRequest = generateRequest("GET", "/file1000");
        boolean isHandled = fileHandler.handle(invalidRequest, response);
        assertFalse(isHandled);
    }

    private HTTPRequest generateRequest(String method, String uri) {
        Map<String, String> parsedComponents = new HashMap<>();
        parsedComponents.put("requestMethod", method.toUpperCase());
        parsedComponents.put("requestURI", uri);
        parsedComponents.put("httpVersion", "HTTP/1.1");
        return new HTTPRequest(parsedComponents, new HashMap<>());
    }

    public class MockFileManager implements FileManager {
        public boolean isFileFound(String fileName) {
            if (fileName.equals("file1") || fileName.equals("image.gif")) {
                return true;
            }
            return false;
        }

        public byte[] getFileContents(String requestURI) {
            return "body".getBytes();
        }

        public String getContentType(String requestURI) {
            if (requestURI.contains("image.gif")) {
                return "image/gif";
            }
            return "text/plain";
        }
    }
}
