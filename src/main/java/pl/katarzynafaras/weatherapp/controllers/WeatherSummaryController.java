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
import pl.katarzynafaras.weatherapp.model.*;
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
    public String addLocation(String city, String country, Model model, Local local) {

        model.addAttribute("weatherSummary", weatherService.getWeatherSummary(country, city));
        model.addAttribute("weatherSummaries", weatherService.getForecast(country,city));
        model.addAttribute("todayWeatherSummaries", weatherService.getListOfTodayWeatherSummaries(country,city));
        return "summary";
    }

}
