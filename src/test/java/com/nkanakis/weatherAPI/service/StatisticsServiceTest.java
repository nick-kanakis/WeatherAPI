package com.nkanakis.weatherAPI.service;

import com.nkanakis.weatherAPI.client.dto.OpenWeatherCity;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecast;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecastInstance;
import com.nkanakis.weatherAPI.client.dto.OpenWeatherForecastInstanceInfo;
import com.nkanakis.weatherAPI.domain.Forecast;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
public class StatisticsServiceTest {

    @InjectMocks
    StatisticsServiceImpl statisticsService;

    private OpenWeatherForecast forecast;

    @Before
    public void setup() {
        forecast = new OpenWeatherForecast();
        forecast.setResponseCode("200");

        LocalDateTime now = LocalDateTime.now();

        OpenWeatherForecastInstance day1 = new OpenWeatherForecastInstance();
        day1.setTime(now.plusDays(1).withHour(15).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo day1Info = new OpenWeatherForecastInstanceInfo();
        day1Info.setPressure(10);
        day1Info.setTemperature(10);
        day1.setInfo(day1Info);

        OpenWeatherForecastInstance day2 = new OpenWeatherForecastInstance();
        day2.setTime(now.plusDays(1).withHour(6).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo day2Info = new OpenWeatherForecastInstanceInfo();
        day2Info.setPressure(20);
        day2Info.setTemperature(20);
        day2.setInfo(day2Info);

        OpenWeatherForecastInstance day3 = new OpenWeatherForecastInstance();
        day3.setTime(now.plusDays(1).withHour(15).withMinute(59).withSecond(59).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo day3Info = new OpenWeatherForecastInstanceInfo();
        day3Info.setPressure(30);
        day3Info.setTemperature(30);
        day3.setInfo(day3Info);


        OpenWeatherForecastInstance night1 = new OpenWeatherForecastInstance();
        night1.setTime(now.plusDays(1).withHour(1).withMinute(59).withSecond(59).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo night1Info = new OpenWeatherForecastInstanceInfo();
        night1Info.setPressure(100);
        night1Info.setTemperature(100);
        night1.setInfo(night1Info);

        OpenWeatherForecastInstance night2 = new OpenWeatherForecastInstance();
        night2.setTime(now.plusDays(1).withHour(5).withMinute(59).withSecond(59).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo night2Info = new OpenWeatherForecastInstanceInfo();
        night2Info.setPressure(200);
        night2Info.setTemperature(200);
        night2.setInfo(night2Info);

        OpenWeatherForecastInstance night3 = new OpenWeatherForecastInstance();
        night3.setTime(now.plusDays(1).withHour(18).withMinute(0).withSecond(0).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo night3Info = new OpenWeatherForecastInstanceInfo();
        night3Info.setPressure(300);
        night3Info.setTemperature(300);
        night3.setInfo(night3Info);

        OpenWeatherForecastInstance outOfRangeDay = new OpenWeatherForecastInstance();
        outOfRangeDay.setTime(now.plusDays(4).atZone(ZoneId.systemDefault()).toEpochSecond());
        OpenWeatherForecastInstanceInfo outOfRangeDayInfo = new OpenWeatherForecastInstanceInfo();
        outOfRangeDayInfo.setPressure(9999);
        outOfRangeDayInfo.setTemperature(9999);
        outOfRangeDay.setInfo(outOfRangeDayInfo);

        OpenWeatherCity city = new OpenWeatherCity();
        city.setCountry("DE");
        city.setName("Berlin");

        List<OpenWeatherForecastInstance> list = new ArrayList<>();
        list.add(day1);
        list.add(day2);
        list.add(day3);
        list.add(night1);
        list.add(night2);
        list.add(night3);
        list.add(outOfRangeDay);


        forecast.setForecastInstances(list);
        forecast.setCity(city);
    }

    @Test
    public void testThreeDayForecastStatistics() {
        Forecast forecast = statisticsService.threeDayForecastStatistics(this.forecast);
        assertEquals(forecast.getAverageDailyTmp(), 20.0);
        assertEquals(forecast.getAverageNightlyTmp(), 200.0);
        assertEquals(forecast.getAveragePressure(), 110.0);
        assertEquals(forecast.getCity(), "Berlin");
    }
}
