package com.nkanakis.weatherAPI.utils;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class InputValidationTest {

    @Test
    public void shouldNotThrowException() {
        InputValidation.cityValidation("Berlin");
        InputValidation.cityValidation("Athens");
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidation.cityValidation("123")).withMessage("Invalid city name");
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidation.cityValidation("Berlin12")).withMessage("Invalid city name");
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidation.cityValidation("berlin//")).withMessage("Invalid city name");
        assertThatIllegalArgumentException().isThrownBy(() -> InputValidation.cityValidation("b/*")).withMessage("Invalid city name");
    }
}
