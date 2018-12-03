package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.OpenWeatherClient;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.domain.Forecast;
import com.nkanakis.weatherAPI.utils.InputValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastServiceImpl implements ForecastService {

    @Autowired
    private OpenWeatherClient owClient;

    @Autowired
    private StatisticsService statisticsService;

    /**
     * @implNote client communication and statistics calculation are separated and abstracted in this way
     * we depend on an abstraction and not in a concrete implementation.
     */
    @Override
    public Forecast get3DayForecast(String city) {
        InputValidation.cityValidation(city);
        OpenWeatherForecast forecast = owClient.getForecast(city);

        if (forecast == null) {
            log.error("External API returned null object for city: " + city);
            throw new IllegalArgumentException("Error while retrieving forecasts for: " + city);

        }
        return statisticsService.threeDayForecastStatistics(forecast);
    }
}
