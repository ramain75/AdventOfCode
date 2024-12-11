package com.adventofcode.year2024.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Day7Test {

    @Test
    void testDay7WithSampleInput() {
        String input = """
                190: 10 19
                3267: 81 40 27
                83: 17 5
                156: 15 6
                7290: 6 8 6 15
                161011: 16 10 13
                192: 17 8 14
                21037: 9 7 18 13
                292: 11 6 16 20""";
        Day7 day7 = new Day7();
        Assertions.assertEquals(3749, day7.getTotalCalibrationResult(input));
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "190: 10 19",
            "3267: 81 40 27",
            "292: 11 6 16 20"
    })
    void testValidCalibrationEquationTrueCases(String input) {
        Day7 day7 = new Day7();
        Assertions.assertTrue(day7.isValidCalibrationEquation(input));
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "83: 17 5",
            "156: 15 6",
            "7290: 6 8 6 15",
            "161011: 16 10 13",
            "192: 17 8 14",
            "21037: 9 7 18 13"
    })
    void testValidCalibrationEquationFalseCases(String input) {
        Day7 day7 = new Day7();
        Assertions.assertFalse(day7.isValidCalibrationEquation(input));
    }

}
