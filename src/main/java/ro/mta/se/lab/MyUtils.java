package ro.mta.se.lab;
import org.json.JSONObject;


/**
 * Class for utility global vars
 */

public class MyUtils {
    public static final String      apiKey           = "a30537bced0b07c2d037a4708ada47ac";
    public static final String      baseUrlBegin     = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String      baseUrlEnd       = "&appid=" + apiKey;
    public static       JSONObject  inputData        = null;
}
