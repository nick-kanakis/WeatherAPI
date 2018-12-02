package com.nkanakis.weatherAPI.utils;


public final class InputValidation {

    public static void cityValidation(String city) {
        if (!city.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid city name");
        }
    }
}
