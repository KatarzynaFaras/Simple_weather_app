package pl.katarzynafaras.weatherapp.controllers;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.katarzynafaras.weatherapp.model.*;
import pl.katarzynafaras.weatherapp.service.WeatherService;

import javax.validation.Valid;


@AllArgsConstructor
@Controller
@RequestMapping("/")
public class WeatherSummaryController {

    private final WeatherService weatherService;


    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/index")
    public String addLocation(@ModelAttribute("form") @Valid FormDTO formDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        } else {
            model.addAttribute("weatherSummary", weatherService.getWeatherSummary(formDTO.getCity()));
            model.addAttribute("weatherSummaries", weatherService.getForecast(formDTO.getCity()));
            model.addAttribute("todayWeatherSummaries", weatherService.getListOfTodayWeatherSummaries(formDTO.getCity()));
            return "summary";
        }
    }

}
