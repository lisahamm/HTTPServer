package server.core.helpers;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha1Test {
    @Test
    public void testItSha1EncodesByteArray() throws Exception {
        String sha1Encoded = Sha1.encode("default content".getBytes());
        assertEquals("dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec", sha1Encoded);
    }
}