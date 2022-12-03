package starb.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class StarbServer {
    public static final int PORT = 8000;

    private static final int numPuzzles = 2;

    public static String[] puzzleFileStrings= new String[numPuzzles];

    public static void main( String[] args ) {
        puzzleFileStrings[0] = "puzzles/puzzle-1-1-1.txt";
        puzzleFileStrings[1] = "puzzles/puzzle-1-1-2.txt";

        HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
        } catch(IOException e ) {
            System.err.println("Unable to start web server on port " + PORT);
            System.err.println(e.getMessage());
            return;
        }
        server.setExecutor(Executors.newCachedThreadPool());

        server.createContext("/puzzles", new PuzzleHandler());

        System.out.println("Starting web server on port: " + PORT);
        server.start();

    }

}
