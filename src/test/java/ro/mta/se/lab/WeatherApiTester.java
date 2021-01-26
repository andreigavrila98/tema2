package ro.mta.se.lab;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class WeatherApiTester {
    public static void main(String[] args) throws URISyntaxException {

        WeatherApiHandler waht = mock(WeatherApiHandler.class);
        when(waht.getWeather(new URI("http://api.openweathermap.org/data/2.5/weather?id=524901&appid=" + MyUtils.apiKey))).thenReturn(new JSONObject("{temp: 300}"));

        var res = waht.getWeather(new URI("http://api.openweathermap.org/data/2.5/weather?id=524901&appid=" + MyUtils.apiKey));
        System.out.println(res);
    }
}
