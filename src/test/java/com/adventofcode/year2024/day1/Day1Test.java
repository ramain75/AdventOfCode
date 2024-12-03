package com.adventofcode.year2024.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    Day1 day1SampleInputSetup() {
        String lists = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;
        return new Day1(lists);
    }

    @Test
    void testDay1Part1SampleInput() {
        assertEquals(11, day1SampleInputSetup().getTotalDistanceBetweenGroups());
    }

    @Test
    void testDay1Part2SampleInput() {
        assertEquals(31, day1SampleInputSetup().getTotalSimilarityScore());
    }
}
