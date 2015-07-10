package server.core.helpers;

import server.core.requests.HeaderParser;
import server.core.requests.Request;

import java.util.Base64;

public class BasicAuth {

    public static boolean isAuthorized(Request request, String validUserID, String validPassword) {
        if (request.getHeaders().containsKey("Authorization")) {
            String encodedCredentials = HeaderParser.parseAuthorizationHeader(request);
            String decodedCredentials = base64Decode(encodedCredentials.trim());

            String userID = parseLoginCredentials(decodedCredentials)[0];
            String password = parseLoginCredentials(decodedCredentials)[1];

            return userID.equals(validUserID) && password.equals(validPassword);
        }
        return false;
    }

    private static String base64Decode(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return new String(decoded);
    }

    private static String[] parseLoginCredentials(String decodedCredentials) {
        return decodedCredentials.split(":", 2);
    }
}
