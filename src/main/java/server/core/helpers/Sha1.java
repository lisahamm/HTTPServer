package server.core.helpers;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha1 {
    public static String encode(byte[] byteArray) {
        try {
            MessageDigest encoder = MessageDigest.getInstance("SHA-1");
            String sha1Encoded = DatatypeConverter.printHexBinary(encoder.digest(byteArray));
            return sha1Encoded.toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
