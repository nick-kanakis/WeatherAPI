package com.nkanakis.weatherAPI.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherForecast {

    @JsonProperty("cod")
    private String responseCode;

    @JsonProperty("list")
    private List<OpenWeatherForecastInstance> forecastInstances;

    @JsonProperty("city")
    private OpenWeatherCity city;
}
