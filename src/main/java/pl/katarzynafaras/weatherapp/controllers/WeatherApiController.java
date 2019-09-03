package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.katarzynafaras.weatherapp.model.WeatherEntry;
import pl.katarzynafaras.weatherapp.model.WeatherForecast;

import pl.katarzynafaras.weatherapp.service.WeatherService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class WeatherApiController {

    private final WeatherService weatherService;

    @GetMapping("/now/{country}/{city}")
    public WeatherEntry getWeather(@PathVariable String country, @PathVariable String city) {
        return this.weatherService.getWeather(country, city);
    }

    @GetMapping("/todayForecast/{country}/{city}")
    public List<WeatherEntry> getTodayForecast(@PathVariable String country,
                                               @PathVariable String city) {

        return this.weatherService.getForecast(country, city).getTodayEntries();
    }

    @GetMapping("/forecast/{country}/{city}")
    public WeatherForecast getHourlyForecast(@PathVariable String country,
                                             @PathVariable String city) {
        return this.weatherService.getForecast(country, city);
    }

}
