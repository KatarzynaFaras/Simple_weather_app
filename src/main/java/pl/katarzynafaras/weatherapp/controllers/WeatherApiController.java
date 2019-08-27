package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.katarzynafaras.weatherapp.model.WeatherEntry;
import pl.katarzynafaras.weatherapp.model.WeatherForecast;

import pl.katarzynafaras.weatherapp.service.WeatherService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class   WeatherApiController {

    private final WeatherService weatherService;

    @RequestMapping("/now/{country}/{city}")
    public WeatherEntry getWeather(@PathVariable String country, @PathVariable String city){
        return this.weatherService.getWeather(country,city);
    }

    @RequestMapping("/forecast/{country}/{city}")
    public WeatherForecast getHourlyForecast(@PathVariable String country,
                                             @PathVariable String city){
        return  this.weatherService.getForecast(country,city);
    }

}
