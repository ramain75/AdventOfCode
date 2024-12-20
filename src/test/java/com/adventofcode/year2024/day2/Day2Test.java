package com.adventofcode.year2024.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day2Test {
    @Test
    void testGetNumSafeReportsSampleInput() {
        String input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9""";

        Day2 day2 = new Day2();
        Assertions.assertEquals(2, day2.getNumSafeReports(input, false));
    }

    @Test
    void testGetNumSafeReportsWithDampenerSampleInput() {
        String input = """
                7 6 4 2 1
                1 2 7 8 9
                9 7 6 2 1
                1 3 2 4 5
                8 6 4 4 1
                1 3 6 7 9""";

        Day2 day2 = new Day2();
        Assertions.assertEquals(4, day2.getNumSafeReports(input, true));
    }

}
