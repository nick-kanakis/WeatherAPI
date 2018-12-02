package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.domain.Forecast;

public interface ForecastService {

    Forecast get3DayForecast(String city);
}
