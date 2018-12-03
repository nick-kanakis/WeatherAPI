[![Build Status](https://travis-ci.org/nicolasmanic/WeatherAPI.svg?branch=master)](https://travis-ci.org/nicolasmanic/TriangleChallenge)
[![codecov](https://codecov.io/gh/nicolasmanic/WeatherApi/branch/master/graph/badge.svg)](https://codecov.io/gh/nicolasmanic/TriangleChallenge)

# Weather API

## Prerequisite
- JDK 8+
- Maven 3+

## Build & Run

- Unit testing
```
mvn clean test
```

- Integration testing
```
mvn clean verify -Pfailsafe -DskipTests
```

- Package to fat jar and run
```
//Creates a WeatherAPI.jar
mvn clean package

//Run jar
java -jar WeatherAPI.jar
```

- Unit testing & Integration testing
```
mvn clean verify -Pfailsafe
```

- Run with maven
```
mvn spring-boot:run
```

API Usage:
```
GET localhost:8080/data/{Valid City Name}

Sample Response:

{
    "city": "Berlin",
    "now": "2018-12-02",
    "daily_average": 6.52,
    "nightly_average": 5.16,
    "pressure_average": 1018.26
}

```

## Design Decisions

The application is based on the Spring Boot framework and can be separated in 3 main parts:
- Rest controller
- Service Layer
- Rest client

### Rest Controller
Exposes only one endpoint to get the 3 day forecast and passes the given city to the Service layer.
Also handles any Exception and returns an appropriate json response to the end user.

### Service Layer
Is used to convert the OpenWeatherApi data to the 3-day summary forecast for this use case. This is done with the
help of the StatisticsService that calculates and summarizes the raw input from the external API. Last but not
least validates the city name provided by the user (checking for invalid characters).

### Rest client
Is responsible for handling the connection and unmarshalling the raw data provided by the Open Weather API.

For more information please see the provided javadocs. Also for specific implementation decisions please see the corresponding
```@ImplNote``` annotation 

## API Documentation

This project uses swagger to auto-generate a documentation please visit the following [LINK](http://localhost:8080/swagger-ui.html) 
on your local running server for more information.

