package starb.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Responsible for generating Http Requests to retrieve new puzzles from the server
 */
public class HttpRequestGenerator {

    /**
     * Constructor for a new HttpRequestGenerator
     * (empty initialization)
     */
    public HttpRequestGenerator() {}

    /**
     * Method returns the response sent from the server. 
     * @return response from the server (a puzzle string)
     * @throws IOException
     * @throws InterruptedException
     */
    public String getNewPuzzle() throws IOException, InterruptedException {
        return sendRequest();
    }

    /**
     * sends an Httprequest to retrieve a new puzzle from the server
     * @return a string representation of a puzzle
     * @throws IOException
     * @throws InterruptedException
     */
    private String sendRequest() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8000/puzzles"))
                .header("Content-Type", "test/plain")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response status: " + response.statusCode());

        return response.body();
    }
}
