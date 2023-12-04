package com.adventofcode.year2023.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    @Test
    void testDay3Part1Sample() {
        String filename = "src/main/resources/2023day3sample.txt";
        Day3 day3 = new Day3(filename);

        assertEquals(4361, day3.getPartNumberTotal(), "Invalid part number total");
    }
}
