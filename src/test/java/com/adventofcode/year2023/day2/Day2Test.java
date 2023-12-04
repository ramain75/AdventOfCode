package com.adventofcode.year2023.day2;

import com.adventofcode.DayMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    @Test
    void testDay2Part1Sample() {
        String filename = "src/main/resources/2023day2sample.txt";
        Day2 day2 = new Day2(filename, DayMode.PART1);

        assertEquals(8, day2.getPossibleGameIdsTotal(), "Game IDs total is wrong");
        assertEquals(2286, day2.getPowersTotal(), "Powers Total is wrong");
    }

    @Test
    void testDay2Part1Input() {
        String filename = "src/main/resources/2023day2input.txt";
        Day2 day2 = new Day2(filename, DayMode.PART1);

        assertEquals(2716, day2.getPossibleGameIdsTotal(), "Game IDs total is wrong");
        assertEquals(72227, day2.getPowersTotal(), "Powers Total is wrong");
    }
}
