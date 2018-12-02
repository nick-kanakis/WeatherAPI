package com.nkanakis.weatherAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
class ForecastDTOTest {

    private String city;

    @JsonProperty("now")
    private LocalDate day;

    @JsonProperty("daily_average")
    private double averageDailyTmp;

    @JsonProperty("nightly_average")
    private double averageNightlyTmp;

    @JsonProperty("pressure_average")
    private double averagePressure;
}
