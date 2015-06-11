package com.lisahamm.application.handlers;

import com.lisahamm.*;
import com.lisahamm.application.handlers.FileHandler;
import com.lisahamm.mocks.MockHTTPRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FileHandlerTest {
    private RequestHandler fileHandler;
    private ResponseBuilder response;
    private Map<String, String> headers;

    @Before
    public void setUp() throws Exception {
        headers = new HashMap<>();
        response = new ResponseBuilder();
        FileManager fileManager = new MockFileManager();
        fileHandler = new FileHandler(fileManager);
    }

    @Test
    public void testItHandlesRequestForFullFileInPublicDirectory() throws Exception {
        Request validRequest = generateRequest("GET", "/file1", headers);
        boolean isHandled = fileHandler.handle(validRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertTrue(response.getBody().length > 0);
        assertTrue(isHandled);
    }

    @Test
    public void testItHandlesRequestForPartialFileContentsInPublicDirectory() throws Exception {
        headers.put("Range", "bytes=0-4");
        Request validRequest = generateRequest("GET", "/file1", headers);

        boolean isHandled = fileHandler.handle(validRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 206 Partial Content\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: text/plain\r\n"));
        assertEquals(5, response.getBody().length);
        assertTrue(isHandled);
    }

    @Test
    public void testHandlesRequestForImageInPublicDirectory() throws Exception {
        Request validRequest = generateRequest("GET", "/image.gif", headers);
        boolean isHandled = fileHandler.handle(validRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 200 OK\r\n"));
        assertTrue(response.getResponseHeader().contains("Content-Type: image/gif\r\n"));
        assertTrue(response.getBody().length > 0);
        assertTrue(isHandled);
    }

    @Test
    public void testHandlesPatchRequest() throws Exception {
        headers.put("If-Match", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");
        headers.put("Content-Length", "15");
        String body = "patched content";
        Request validPatchRequest = generateRequestWithBody("PATCH", "/patch-content.txt", headers, body);

        boolean isHandled = fileHandler.handle(validPatchRequest, response);

        assertTrue(response.getResponseHeader().contains("HTTP/1.1 204 No Content"));
//        assertTrue(response.getResponseHeader().contains("ETag"));
//        assertTrue(response.getBody().toString().equals("patched content"));
        assertTrue(isHandled);
    }

    @Test
    public void testSha1Encoding() throws Exception {
        FileManager fileManager = new MockFileManager();
        FileHandler fileHandler = new FileHandler(fileManager);
        String validEncoding = "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec";

        String encodingResult = fileHandler.sha1Encoding("default content".getBytes());

        assertEquals(validEncoding, encodingResult);
    }

    @Test
    public void testItHandlesRequestForMethodNotAllowed() throws Exception {
        Request invalidRequest = generateRequest("POST", "/file1", headers);
        boolean isHandled = fileHandler.handle(invalidRequest, response);
        assertTrue(response.getResponseHeader().contains("HTTP/1.1 405 Method Not Allowed"));
        assertTrue(isHandled);
    }

    @Test
    public void testDoesNotHandleRequestIfUriDoesNotExistInDirectory() throws Exception {
        Request invalidRequest = generateRequest("GET", "/file1000", headers);
        boolean isHandled = fileHandler.handle(invalidRequest, response);
        assertFalse(isHandled);
    }

    private MockHTTPRequest generateRequest(String method, String uri, Map<String, String> headers) {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = method.toUpperCase();
        request.requestURI = uri;
        request.headers = headers;
        return request;
    }

    private MockHTTPRequest generateRequestWithBody(String method, String uri, Map<String, String> headers, String body) {
        MockHTTPRequest request = new MockHTTPRequest();
        request.requestMethod = method.toUpperCase();
        request.requestURI = uri;
        request.headers = headers;
        request.body = body;
        return request;
    }


    public class MockFileManager implements FileManager {
        public boolean isFileFound(String fileName) {
            if (fileName.equals("file1") || fileName.equals("image.gif") || fileName.equals("patch-content.txt")) {
                return true;
            }
            return false;
        }

        public File getFile(String requestURI) {
            return new File(requestURI);
        }

        public void overwriteFile(String requestURI, String newContents) {

        }

        public byte[] getFileContents(String requestURI) {
            if (requestURI.contains("patch-content.txt")) {
                return "default content".getBytes();
            } else {
                return "file1 contents".getBytes();
            }
        }

        public byte[] getPartialFileContents(String requestURI, String range) {
            return "file1".getBytes();
        }

        public String getContentType(String requestURI) {
            if (requestURI.contains("image.gif")) {
                return "image/gif";
            }
            return "text/plain";
        }
    }
}