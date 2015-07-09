package server.core.configuration;

import org.junit.Test;

import static org.junit.Assert.*;

public class SettingsTest {
    private String[] args = {"-p", "4000", "-d", "/public_directory"};

    @Test
    public void testItConfiguresThePortFromArgumentsFromCommandLineArguments() throws Exception {
        Settings.configureFromCommandLine(args);

        assertEquals(4000, Settings.portNumber);
    }

    @Test
    public void testItConfiguresThePublicDirectoryFromCommandLineArguments() throws Exception {
        Settings.configureFromCommandLine(args);

        assertEquals("/public_directory", Settings.publicDirectory);
    }

    @Test
    public void testPortHasValueInTheAbsenceOfCommandLineArgument() throws Exception {
        int originalPortNumber = Settings.portNumber;
        String[] emptyArgs = {};

        Settings.configureFromCommandLine(emptyArgs);

        assertEquals(originalPortNumber, Settings.portNumber);
    }

    @Test
    public void testPublicDirectoryHasValueInTheAbsenceOfCommandLineArgument() throws Exception {
        String originalPublicDirectory = Settings.publicDirectory;
        String[] emptyArgs = {};

        Settings.configureFromCommandLine(emptyArgs);

        assertEquals(originalPublicDirectory, Settings.publicDirectory);
    }
}