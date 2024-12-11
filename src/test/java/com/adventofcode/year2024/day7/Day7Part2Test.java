package com.adventofcode.year2024.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Day7Part2Test {

    @Test
    void testDay7Part2WithSampleInput() {
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
        Day7Part2 day7 = new Day7Part2();
        Assertions.assertEquals(3749, day7.getTotalCalibrationResult(input, 2));
        Assertions.assertEquals(11387, day7.getTotalCalibrationResult(input, 3));
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "190: 10 19",
            "3267: 81 40 27",
            "292: 11 6 16 20"
    })
    void testValidCalibrationEquation2OperatorsTrueCases(String input) {
        Day7Part2 day7 = new Day7Part2();
        Assertions.assertTrue(day7.isValidCalibrationEquation(input, 2) > 0);
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
    void testValidCalibrationEquation2OperatorsFalseCases(String input) {
        Day7Part2 day7 = new Day7Part2();
        Assertions.assertFalse(day7.isValidCalibrationEquation(input, 2) > 0);
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "190: 10 19",
            "3267: 81 40 27",
            "156: 15 6",
            "7290: 6 8 6 15",
            "192: 17 8 14",
            "292: 11 6 16 20"
    })
    void testValidCalibrationEquation3OperatorsTrueCases(String input) {
        Day7Part2 day7 = new Day7Part2();
        Assertions.assertTrue(day7.isValidCalibrationEquation(input, 3) > 0);
    }

    @ParameterizedTest()
    @ValueSource(strings = {
            "83: 17 5",
            "161011: 16 10 13",
            "21037: 9 7 18 13"
    })
    void testValidCalibrationEquation3OperatorsFalseCases(String input) {
        Day7Part2 day7 = new Day7Part2();
        Assertions.assertFalse(day7.isValidCalibrationEquation(input, 3) > 0);
    }
}
