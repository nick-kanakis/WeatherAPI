package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.domain.Forecast;

interface StatisticsService {

    Forecast threeDayForecastStatistics(OpenWeatherForecast owForecast);
}
