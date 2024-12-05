package com.adventofcode.year2024.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day4Part2Test {
    @Test
    void testCountXmasInstancesInWordSearchSampleInput() {
        String grid = """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX""";
        Assertions.assertEquals(9, new Day4Part2().countXmasInstancesInWordSearch(grid));
    }
}
