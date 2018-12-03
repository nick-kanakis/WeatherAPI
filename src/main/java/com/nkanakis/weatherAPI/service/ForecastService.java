package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.domain.Forecast;

public interface ForecastService {
    /**
     * Returns a 3-day {@link Forecast}.
     * <p>
     * First validates any provided input, then calls the Rest client and if there is no error.
     * calls statistics service to calculate the average temperatures and pressure.
     *
     * @param city city name
     * @return a 3-day average forecast.
     * @throws IllegalArgumentException if any error happens during validation of city or in case the result from the
     *                                  Rest client is null
     */
    Forecast get3DayForecast(String city);
}
