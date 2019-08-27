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

import java.util.ArrayList;
import java.util.List;


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

        WeatherEntry weather = this.weatherService.getWeather(location.getCountry(), location.getCity());
        WeatherSummary weatherSummary = createWeatherSummary(location, weather);
        model.addAttribute("weatherSummary", weatherSummary);


        WeatherForecast weatherForecast = this.weatherService.getForecast(location.getCountry(), location.getCity());
        List<WeatherEntry> entriesList = weatherForecast.getEntries();
        List<WeatherSummary> weatherSummaries = new ArrayList<>();
        for (WeatherEntry entry: entriesList) {
            if(entry.getTimestamp().getDayOfMonth()==(weather.getTimestamp().getDayOfMonth()))
            weatherSummaries.add(createWeatherSummary(location,entry));
        }
        model.addAttribute("weatherSumaries",weatherSummaries);

        return "summary";
    }




    private WeatherSummary createWeatherSummary(Location location, WeatherEntry weather) {
        return new WeatherSummary(location, weather);
    }

//    private List<WeatherSummary> createListOfWeatherSummary(Location location, Weather weather) {
//        List<WeatherSummary> listOfWeatherSummery = new ArrayList<>();
//        listOfWeatherSummery.add(location, )
//
//        return new WeatherSummary(location, weather);
//    }
}
