package com.adventofcode.year2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.adventofcode.DayMode;
import com.adventofcode.year2023.day1.Day1;
import org.junit.jupiter.api.Test;

public class Day1Test {

    @Test
    void testDay1Part1Sample() {
        String filename = "src/main/resources/2023day1sample.txt";
        Day1 day1 = new Day1(filename, DayMode.PART1);

        assertEquals(142, day1.getTotal(), "Total is wrong");
    }

    @Test
    void testDay1Part2Sample() {
        String filename = "src/main/resources/2023day1sample2.txt";
        Day1 day1 = new Day1(filename, DayMode.PART2);

        assertEquals(281, day1.getTotal(), "Total is wrong");
    }

    @Test
    void testDay1Part2MoreComplexSample() {
        String filename = "src/main/resources/2023day1sample3.txt";
        Day1 day1 = new Day1(filename, DayMode.PART2);

        assertEquals(82, day1.getTotal(), "Total is wrong");
    }

    @Test
    void testDay1Part1Input() {
        String filename = "src/main/resources/2023day1input.txt";
        Day1 day1 = new Day1(filename, DayMode.PART1);

        assertEquals(54990, day1.getTotal(), "Total is wrong");
    }

    @Test
    void testDay1Part2Input() {
        String filename = "src/main/resources/2023day1input.txt";
        Day1 day1 = new Day1(filename, DayMode.PART2);

        assertEquals(54489, day1.getTotal(), "Total is wrong");
    }
}
