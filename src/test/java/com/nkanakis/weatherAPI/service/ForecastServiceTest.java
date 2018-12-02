package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.OpenWeatherClient;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.domain.Forecast;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ForecastServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @InjectMocks
    ForecastServiceImpl forecastService;
    @Mock
    OpenWeatherClient openWeatherClient;
    @Mock
    StatisticsService statisticsService;

    @Test
    public void shouldReturnForecastWithoutException() {
        when(openWeatherClient.getForecast(any())).thenReturn(new OpenWeatherForecast());
        when(statisticsService.threeDayForecastStatistics(any())).thenReturn(Forecast.builder().city("Berlin").build());
        Forecast berlinForecast = forecastService.get3DayForecast("Berlin");
        assertEquals(berlinForecast.getCity(), "Berlin");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNull() {
        when(openWeatherClient.getForecast(any())).thenReturn(null);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Error while retrieving forecasts for: Berlin");
        Forecast berlinForecast = forecastService.get3DayForecast("Berlin");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForInvalidCityName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid city name");
        Forecast berlinForecast = forecastService.get3DayForecast("Berlin$");
    }
}
