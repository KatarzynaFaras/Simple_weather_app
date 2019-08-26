package pl.katarzynafaras.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class WeatherSummary {

    private final String country;
    private final String city;
    private final Integer code;
    private final String icon;
    private final double temperature;
    private final String description;

    public WeatherSummary(Location location, Weather weather) {
        this.country = location.getCountry();
        this.city = location.getCity();
        this.code = weather.getWeatherId();
        this.icon = weather.getWeatherIcon();
        this.temperature = weather.getTemperature();
        this.description = weather.getDescription();
    }

    public String getCelsiusTemperature(){
        double celsiusTemp = this.temperature -273.15;
        return String.valueOf(Math.round(celsiusTemp));
    }
}
