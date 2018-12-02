package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecastInstance;
import com.nkanakis.weatherAPI.domain.Forecast;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public Forecast threeDayForecastStatistics(OpenWeatherForecast owForecast) {
        return Forecast.builder()
                .averageDailyTmp(getAverageDailyTmp(owForecast.getForecastInstances()))
                .averageNightlyTmp(getAverageNightlyTmp(owForecast.getForecastInstances()))
                .averagePressure(getAveragePressure(owForecast.getForecastInstances()))
                .city(owForecast.getCity().getName())
                .day(LocalDate.now())
                .build();
    }

    private double getAverageDailyTmp(List<OpenWeatherForecastInstance> forecastInstances) {
        return forecastInstances.stream().filter(f -> isWithinNext3Days(f.getTime()))
                .filter(f -> isDayTime(f.getTime()))
                .mapToDouble(f -> f.getInfo().getTemperature())
                .summaryStatistics().getAverage();
    }

    private double getAverageNightlyTmp(List<OpenWeatherForecastInstance> forecastInstances) {
        return forecastInstances.stream().filter(f -> isWithinNext3Days(f.getTime()))
                .filter(f -> !isDayTime(f.getTime()))
                .mapToDouble(f -> f.getInfo().getTemperature())
                .summaryStatistics().getAverage();
    }

    private double getAveragePressure(List<OpenWeatherForecastInstance> forecastInstances) {
        return forecastInstances.stream().filter(f -> isWithinNext3Days(f.getTime()))
                .mapToDouble(f -> f.getInfo().getPressure())
                .summaryStatistics().getAverage();
    }

    private boolean isWithinNext3Days(long time) {
        LocalDateTime dt =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());

        return dt.isBefore(LocalDateTime.now().plusDays(3)) && dt.isAfter(LocalDateTime.now());
    }

    private boolean isDayTime(long time) {
        LocalDateTime dt =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault());

        int hour = dt.getHour();
        return hour >= 6 && hour <= 17;

    }
}
