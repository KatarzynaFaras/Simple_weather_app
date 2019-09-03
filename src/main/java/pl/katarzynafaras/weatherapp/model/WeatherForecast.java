package pl.katarzynafaras.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class WeatherForecast implements Serializable {

    private String name;
    private List<WeatherEntry> entries = new ArrayList<>();

    @JsonProperty("entries")
    public List<WeatherEntry> getEntries() {
        return entries;
    }

    @JsonSetter("list")
    public void setEntries(List<WeatherEntry> entries) {
        this.entries = entries;
    }

    @JsonProperty("todayEntries")
    public List<WeatherEntry> getTodayEntries() {
        return this.entries.stream().filter(entry -> entry.getTimestamp().getDayOfMonth()== LocalDateTime.now().getDayOfMonth()).collect(Collectors.toList());
    }


    @JsonProperty("city")
    public void setCity(Map<String, Object> city) {
        this.entries.forEach(entry -> entry.setName(city.get("name").toString()));
        setName(city.get("name").toString());
    }}


//
//    @JsonProperty("day")
//    public void setDay(Map<String, Object> name) {
//        setName(name.get("name").toString());
//    }
//}
