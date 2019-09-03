package pl.katarzynafaras.weatherapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import pl.katarzynafaras.weatherapp.WeatherAppProperties;
import pl.katarzynafaras.weatherapp.model.WeatherEntry;
import pl.katarzynafaras.weatherapp.model.WeatherForecast;
import pl.katarzynafaras.weatherapp.model.WeatherSummary;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Service
public class WeatherService {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
    private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

    private final RestTemplate restTemplate;
    private final String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherService(RestTemplateBuilder restTemplateBuilder, WeatherAppProperties weatherAppProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiKey = weatherAppProperties.getKey();
    }

    @Cacheable("weather")
    public WeatherEntry getWeather(String country, String city) {
        logger.info("Requesting current weather for {}/{}", country, city);
        URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
        return invoke(url, WeatherEntry.class);
    }

    public WeatherSummary getWeatherSummary(String country, String city) {
        return new WeatherSummary(country, city, getWeather(country, city));
    }

    @Cacheable("forecast")
    public WeatherForecast getForecast(String country, String city) {
        logger.info("Requesting weather forecast for {}/{}", country, city);
        URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
        return invoke(url, WeatherForecast.class);
    }


    public List<WeatherEntry> getListOfTodayWeatherEntries(String country, String city) {
        return getForecast(country, city).getTodayEntries();
    }


    public List<WeatherSummary> getListOfTodayWeatherSummaries(String country, String city) {

        List<WeatherEntry> todayWeatherEntryList = getListOfTodayWeatherEntries(country, city);
        List<WeatherSummary> weatherSummaries = new ArrayList<>();
        todayWeatherEntryList.forEach(weatherEntry -> weatherSummaries.add(new WeatherSummary(country, city, weatherEntry)));

        return weatherSummaries;
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }
}
