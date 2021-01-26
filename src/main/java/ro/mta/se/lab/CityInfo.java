package ro.mta.se.lab;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.Locale;

/**
 * Model class of the weather information for the cities
 */
public class CityInfo {

    private String name;
    private String countryCode;

    private BigDecimal windSpeed;
    private BigDecimal windDeg;
    private BigDecimal temp;
    private String rainfall;

    private CityInfo(){}
    public  CityInfo(String jsonInfo){
        var jo              = new JSONObject(jsonInfo);

        this.name           = jo.getJSONObject("sys").getString("country").toLowerCase(Locale.ROOT);
        this.countryCode    = jo.getString("name");
        this.windDeg        = jo.getJSONObject("wind").getBigDecimal("deg");
        this.windSpeed      = jo.getJSONObject("wind").getBigDecimal("speed");
        this.temp           = jo.getJSONObject("main").getBigDecimal("temp");
        this.rainfall       = jo.getJSONArray("weather").getJSONObject(0).getString("description");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(BigDecimal windDeg) {
        this.windDeg = windDeg;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }
}
