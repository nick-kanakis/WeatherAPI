package com.nkanakis.weatherAPI.client;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class OpenWeatherClientImpl implements OpenWeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @implNote All values are externalized in a yml file this is done to avoid any hardcoded values that may need to change
     * in the future
     */
    @Value("${client.open-weather.url}")
    private String openWeatherEndpoint;

    @Value("${client.open-weather.units}")
    private String unit;

    @Value("${client.open-weather.appId}")
    private String appId;

    /**
     * @implNote URI generation is separate in it's own method in order to follow the single responsibility principle
     */
    @Override
    public OpenWeatherForecast getForecast(String city) {
        log.info("Retrieve forecast data for: " + city);
        ResponseEntity<OpenWeatherForecast> forecastResponseEntity = restTemplate.getForEntity(generateOpenWeatherURI(city), OpenWeatherForecast.class);
        return forecastResponseEntity.getBody();
    }

    private URI generateOpenWeatherURI(String city) {
        return UriComponentsBuilder.fromHttpUrl(openWeatherEndpoint)
                .queryParam("q", city)
                .queryParam("units", unit)
                .queryParam("appid", appId)
                .build().toUri();
    }
}
