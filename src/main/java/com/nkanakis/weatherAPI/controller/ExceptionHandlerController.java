package com.nkanakis.weatherAPI.controller;

import com.nkanakis.weatherAPI.controller.dto.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> invalidInput(IllegalArgumentException e) {
        logger.error("Invalid input", e);
        ExceptionResponse er = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .message("Invalid input")
                .build();
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RestClientException.class})
    protected ResponseEntity<Object> openWeatherAPIError(RestClientException e) {
        logger.error("Error while connecting to REST API", e);
        ExceptionResponse er = ExceptionResponse.builder()
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .message("Error while connecting to REST API")
                .build();
        return new ResponseEntity<>(er, HttpStatus.SERVICE_UNAVAILABLE);
    }

    //handle all exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        ExceptionResponse er = ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .message("General Error")
                .build();
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
