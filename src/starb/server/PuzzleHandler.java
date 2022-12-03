package starb.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import starb.puzzle.Parser;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Random;

public class PuzzleHandler implements HttpHandler{

    /**
     * Method chooses a random puzzle and calls onto the 
     * parser to send the response puzzle as a string.
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();
        String fileName = path;
        int responseCode = 200;

        Parser parse = new Parser();
        Random randPuz = new Random();
        int puzzleIndex = randPuz.nextInt(StarbServer.puzzleFileStrings.length);
        String response = parse.puzzleFileToString(StarbServer.puzzleFileStrings[puzzleIndex]);

        if (response == null || response.length() <= 0) {
            responseCode = 404;
            response = "Error 404: File not found :(\n";
        }

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.add("Content-Type", "text/plain");

        exchange.sendResponseHeaders(responseCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
