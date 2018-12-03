package com.nkanakis.weatherAPI.client;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import org.springframework.web.client.RestClientException;

public interface OpenWeatherClient {

    /**
     * Retrieves the 5-day forecast from Open Weather API
     * <p>
     * The Open weather API provides many field useless for our forecast generation. So we ignore many
     * provided fields and keep only the absolute necessary.
     *
     * @param city city name
     * @return a striped down DTO version of the Open Weather API result.
     * @throws RestClientException in case unmarshalling or communication with the API fails.
     */
    OpenWeatherForecast getForecast(String city);
}
