package com.nkanakis.weatherAPI.controller;


import com.nkanakis.weatherAPI.domain.Forecast;
import com.nkanakis.weatherAPI.service.ForecastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(value = "Forecast API", description = "Forecast operations available on this level")
public class ForecastController {

    @Autowired
    private ForecastService forecastService;

    @GetMapping(path = "/data/{city}")
    @ApiOperation(value = "Get 3-days forcast by city name",
            response = Forecast.class)
    public ResponseEntity<Forecast> get3DayForecast(@PathVariable final String city) {
        log.info("Create 3-day forecast for: " + city);
        Forecast f = forecastService.get3DayForecast(city);
        return ResponseEntity.ok(f);
    }

}
