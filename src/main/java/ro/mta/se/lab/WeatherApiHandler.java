package ro.mta.se.lab;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

/**
 * Class for handling the API calls to the OpenWeatherMap service
 */
public class WeatherApiHandler {

    /**
     * Get the weather information in JSON format from the WebService
     * @param  uri The URI of the request
     * @return The JSON object containing the weather information
     * @throws URISyntaxException
     * @throws JSONException
     */
    public JSONObject getWeather(URI uri) throws URISyntaxException, JSONException {
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        var res = client.sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        return new JSONObject(res);
    }
}
