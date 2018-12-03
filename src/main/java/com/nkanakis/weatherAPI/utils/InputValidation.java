package com.nkanakis.weatherAPI.utils;


public final class InputValidation {

    /**
     * Validates that the provided city name does not contain illegal characters.
     * <p>
     * A city name can only contain alphabetical characters.
     *
     * @param city city name
     * @throws IllegalArgumentException for invalid input
     */
    public static void cityValidation(String city) {
        if (!city.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid city name");
        }
    }
}
