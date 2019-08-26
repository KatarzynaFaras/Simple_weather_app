package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.katarzynafaras.weatherapp.WeatherAppProperties;
import pl.katarzynafaras.weatherapp.model.Location;
import pl.katarzynafaras.weatherapp.model.Weather;
import pl.katarzynafaras.weatherapp.model.WeatherSummary;
import pl.katarzynafaras.weatherapp.service.WeatherService;


@AllArgsConstructor
@Controller
@RequestMapping("/")
public class WeatherSummaryController {

    private final WeatherService weatherService;
    private final WeatherAppProperties properties;


    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @PostMapping("/index")
    public String addLocation(@ModelAttribute(name = "location") Location location, Model model, Local local) {
        Weather weather = this.weatherService.getWeather(location.getCountry(), location.getCity());
        WeatherSummary weatherSummary = createWeatherSummary(location, weather);
        model.addAttribute("weatherSummary", weatherSummary);
        return "summary";
    }


    private WeatherSummary createWeatherSummary(Location location, Weather weather) {
        return new WeatherSummary(location, weather);
    }
}
