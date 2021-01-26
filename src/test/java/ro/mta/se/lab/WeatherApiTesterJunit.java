package ro.mta.se.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 * JUnit test class
 */
public class WeatherApiTesterJunit {
    /**
     * First function to test the API handler class
     * Tests multiple id interrogation
     * @throws URISyntaxException
     */
    @Test
    public void test1() throws URISyntaxException {
        var wah = new WeatherApiHandler();

        String res = MyUtils.baseUrlBegin;
        res = res.replaceFirst("weather\\?q=", "group\\?id="); // Multiple id interrogation


        Assertions.assertEquals(wah.getWeather(new URI(res                  + "668737,1697734" + MyUtils.baseUrlEnd)).getInt("cnt"), 2);
    }

    /**
     * Second function to test the API handler class
     * Tests the accuracy of the value of the name returned by the Handler class
     * @throws URISyntaxException
     */
    @Test
    public void test2() throws URISyntaxException {
        var wah = new WeatherApiHandler();
        Assertions.assertEquals(wah.getWeather(new URI(MyUtils.baseUrlBegin + "London" + MyUtils.baseUrlEnd)).getString("name"), "London");
    }

    /**
     * Second function to test the API handler class
     * Tests the accuracy of the value of the country code returned by the Handler class
     * @throws URISyntaxException
     */
    @Test
    public void test3() throws URISyntaxException {
        var wah = new WeatherApiHandler();
        Assertions.assertEquals(wah.getWeather(new URI(MyUtils.baseUrlBegin + "Moscow,ru" + MyUtils.baseUrlEnd)).getJSONObject("sys").getString("country").toLowerCase(Locale.ROOT), "ru");
    }
}
