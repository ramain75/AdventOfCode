package com.adventofcode.year2023.day3;

import com.adventofcode.DayMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    @Test
    void testDay3Part1Sample() {
        String filename = "src/main/resources/2023day3sample.txt";
        Day3 day3 = new Day3(filename, DayMode.PART1);

        assertEquals(4361, day3.getPartNumberTotal(), "Invalid part number total");
    }

    @Test
    void testDay3Part2Sample() {
        String filename = "src/main/resources/2023day3sample.txt";
        Day3 day3 = new Day3(filename, DayMode.PART2);

        assertEquals(467835, day3.getPartNumberTotal(), "Invalid part number total");
    }

    @Test
    void testDay3Part1Input() {
        String filename = "src/main/resources/2023day3input.txt";
        Day3 day3 = new Day3(filename, DayMode.PART1);

        // not 453418
        assertEquals(520135, day3.getPartNumberTotal(), "Invalid part number total");
    }
}
