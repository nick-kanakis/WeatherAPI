package com.nkanakis.weatherAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class WeatherAPIApplicationIT {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetForecastForNext3Days() {
        ResponseEntity<ForecastDTOTest> forecastResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/data/Berlin", ForecastDTOTest.class);

        assertEquals(forecastResponseEntity.getStatusCode(), HttpStatus.OK);
        assertNotNull(forecastResponseEntity.getBody());

        ForecastDTOTest forecast = forecastResponseEntity.getBody();

        assertTrue(forecast.getAveragePressure() > 0);
        assertTrue(forecast.getAverageDailyTmp() > 0);
        assertTrue(forecast.getAverageNightlyTmp() > 0);
        assertEquals(forecast.getCity(), "Berlin");
    }

    @Test
    public void shouldReturnBadRequestForInvalidCityName() {
        ResponseEntity<ForecastDTOTest> forecastResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/data/Berl_in", ForecastDTOTest.class);
        assertEquals(forecastResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldReturnBadRequestForUnknownCityName() {
        ResponseEntity<ForecastDTOTest> forecastResponseEntity = restTemplate.getForEntity("http://localhost:" + port + "/data/NoNameCity", ForecastDTOTest.class);
        assertEquals(forecastResponseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
