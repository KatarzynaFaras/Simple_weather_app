package pl.katarzynafaras.weatherapp.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class FormDTO {
    @NotEmpty
    @Size(min=2)
    String city;
}
