package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.domain.Forecast;

interface StatisticsService {

    /**
     * Calculates the 3-day average forecast.
     * <p>
     * It processes the Open Weather API data and calculates the average day/night temperature and average pressure
     * for the next 3 days.
     *
     * @param owForecast open weather forecast
     * @return a 3-day average forecast.
     */
    Forecast threeDayForecastStatistics(OpenWeatherForecast owForecast);
}
