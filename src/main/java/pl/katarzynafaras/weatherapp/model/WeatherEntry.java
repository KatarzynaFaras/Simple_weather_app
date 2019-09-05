package pl.katarzynafaras.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Data
public class WeatherEntry implements Serializable {

    private String city;
    private String name;
    private LocalDateTime timestamp;
    private double temperature;
    private Integer weatherId;
    private String weatherIcon;
    private String description;

    @JsonProperty("timestamp")
    public LocalDateTime getTimestamp() {

        return this.timestamp;
    }

    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixTime * 1000), ZoneId.of("UTC+02:00"));
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
    }

    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));
        setWeatherIcon((String) weather.get("icon"));
        setDescription((String) weather.get("description"));
    }

    @JsonProperty("name")
    public void setName(String name1) {
        this.city = name1;
    }
}


