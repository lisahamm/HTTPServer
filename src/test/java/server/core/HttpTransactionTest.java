package server.core;

import org.junit.Before;
import org.junit.Test;
import server.core.connections.ConnectionIO;
import server.core.requests.*;
import server.core.response.ResponseBuilder;
import server.mocks.*;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.*;

public class HttpTransactionTest {
    private MockClientConnection connection;
    private MockRequestParser parser;
    private ResponseBuilder response;
    private MockRouter router;
    private MockLogger logger;
    private HttpTransaction httpTransaction;

    @Before
    public void setUp() throws Exception {
        connection = new MockClientConnection();
        parser = new MockRequestParser();
        response = new ResponseBuilder();
        router = new MockRouter();
        logger = new MockLogger();
        httpTransaction = new HttpTransaction(connection, parser, response, router, logger);
    }

    @Test
    public void testItDelegatesToConnectionIOToOpenClientConnection() throws Exception {
        httpTransaction.run();

        assertTrue(connection.isInputReaderOpened);
        assertTrue(connection.isOutputStreamOpened);
    }

    @Test
    public void testItDelegatesToLoggerToLogAnIncomingRequest() throws Exception {
        httpTransaction.run();

        assertTrue(logger.isEntryAdded);
    }

    @Test
    public void testItDelegatesToParserToGenerateParsedRequestObject() throws Exception {
        httpTransaction.run();

        assertTrue(parser.isParsedRequestGenerated);
    }

    @Test
    public void testItInvokesRouter() throws Exception {
        httpTransaction.run();

        assertTrue(router.isInvoked);
    }

    @Test
    public void testItDelegatesToConnectionToSendResponse() throws Exception {
        httpTransaction.run();

        assertTrue(connection.isResponseSent);
    }
}