package com.nkanakis.weatherAPI.client;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;

public interface OpenWeatherClient {

    OpenWeatherForecast getForecast(String city);
}
