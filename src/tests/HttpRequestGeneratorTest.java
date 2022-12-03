package tests;

import org.junit.Before;
import org.junit.Test;
import starb.client.HttpRequestGenerator;
import starb.server.StarbServer;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpRequestGeneratorTest {
    HttpRequestGenerator hrg;

    @Before
    public void setUp() throws Exception {
        hrg = new HttpRequestGenerator();
    }

    /**
     *ensures that the requestGenerator sends the correct request to the server and receives the proper value
     * manual test, check the console to ensure proper results
     * ensures that the method does not return an empty string
     */
    @Test
    public void getNewPuzzle() throws IOException, InterruptedException {
        System.out.println(hrg.getNewPuzzle()); //Manual Test. Verify that the right String format is being printed
        assertFalse(hrg.getNewPuzzle().equals(""));
    }
}