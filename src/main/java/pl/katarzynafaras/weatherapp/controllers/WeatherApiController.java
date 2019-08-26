package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.katarzynafaras.weatherapp.model.Weather;
import pl.katarzynafaras.weatherapp.service.WeatherService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/weather")
public class   WeatherApiController {

    private final WeatherService weatherService;

    @RequestMapping("/now/{country}/{city}")
    public Weather getWeather(@PathVariable String country, @PathVariable String city){
        return this.weatherService.getWeather(country,city);
    }

}
