package com.adventofcode.year2024.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    @Test
    void testSumMultiplyInstructionsInCorruptMemoryWithSampleData() {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        Day3 day3 = new Day3();
        assertEquals(161L, day3.sumMultiplyInstructionsInCorruptMemory(input));
    }

    @Test
    void testSumMultiplyInstructionsInCorruptMemoryWithMultiLineSampleData() {
        String input = """
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
                xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""";
        Day3 day3 = new Day3();
        assertEquals(322L, day3.sumMultiplyInstructionsInCorruptMemory(input));
    }

    @Test
    void testSumMultiplyInstructionsInCorruptMemoryWithDoDontWithSampleData() {
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
        Day3 day3 = new Day3();
        assertEquals(48L, day3.sumMultiplyInstructionsInCorruptMemoryWithDoDont(input));
    }
}
