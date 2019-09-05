package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.katarzynafaras.weatherapp.model.WeatherEntry;
import pl.katarzynafaras.weatherapp.model.WeatherForecast;
import pl.katarzynafaras.weatherapp.service.WeatherService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

    private final WeatherService weatherService;

    @GetMapping("/now/{city}")
    public WeatherEntry getWeather(@PathVariable String city) {

        return this.weatherService.getWeather(city);
    }

    @GetMapping("/today/{city}")
    public List<WeatherEntry> getTodayForecast(@PathVariable String city) {

        return this.weatherService.getForecast(city).getTodayEntries();
    }

    @GetMapping("/forecast/{city}")
    public WeatherForecast getHourlyForecast(@PathVariable String city) {
        return this.weatherService.getForecast(city);
    }

}
