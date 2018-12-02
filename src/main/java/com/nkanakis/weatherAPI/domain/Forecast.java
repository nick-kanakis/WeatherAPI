package com.nkanakis.weatherAPI.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@ApiModel(value = "Next days forecast", description = "Can be used for multiple days forecast")
public final class Forecast {

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
