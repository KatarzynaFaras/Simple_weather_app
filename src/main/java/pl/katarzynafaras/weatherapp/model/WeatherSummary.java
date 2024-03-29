package pl.katarzynafaras.weatherapp.model;


import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class WeatherSummary {

    private final String city;
    private final LocalDateTime date;
    private final Integer code;
    private final String icon;
    private final double temperature;
    private final String description;

    public WeatherSummary(String city, WeatherEntry weather) {

        this.city = city;
        this.date = weather.getTimestamp();
        this.code = weather.getWeatherId();
        this.icon = weather.getWeatherIcon();
        this.temperature = weather.getTemperature();
        this.description = weather.getDescription();
    }

    public String getDateAndTime() {
        String dateAndTime = this.date.format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        return dateAndTime;
    }

    public String getTime() {
        String dateAndTime = this.date.format(DateTimeFormatter.ofPattern("HH:mm"));
        return dateAndTime;
    }


    public String getCelsiusTemperature() {
        double celsiusTemp = this.temperature - 273.15;
        return String.valueOf(Math.round(celsiusTemp));
    }
}
