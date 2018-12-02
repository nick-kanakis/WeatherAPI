package com.nkanakis.weatherAPI.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherCity {

    @JsonProperty("country")
    private String country;

    @JsonProperty("name")
    private String name;
}
