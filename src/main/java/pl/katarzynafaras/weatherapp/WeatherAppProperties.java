package pl.katarzynafaras.weatherapp;



import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import javax.validation.constraints.NotNull;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "app.weather")
public class WeatherAppProperties {


    @NotNull
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
