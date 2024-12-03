package com.adventofcode.year2022.day11;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    @Disabled
    @Test
    void testSampleFilePart1() {
        String filename = "src/main/resources/2022day11sample.txt";
        Day11 day11 = new Day11(filename, 20, Day11.Day11Mode.PART1);
        assertEquals(BigInteger.valueOf(10605L), day11.calculateMonkeyBusiness());
    }

    @Disabled
    @Test
    void testInputFilePart1() {
        String filename = "src/main/resources/2022day11input.txt";
        Day11 day11 = new Day11(filename, 20, Day11.Day11Mode.PART1);
        assertEquals(BigInteger.valueOf(110264L), day11.calculateMonkeyBusiness());
    }

    @Disabled
    @Test
    void testSampleFilePart2() {
        String expectedResult = "Monkey 0 inspected items 52166 times.\n" +
                "Monkey 1 inspected items 47830 times.\n" +
                "Monkey 2 inspected items 1938 times.\n" +
                "Monkey 3 inspected items 52013 times.\n";
        String filename = "src/main/resources/2022day11sample.txt";
        Day11 day11 = new Day11(filename, 10000, Day11.Day11Mode.PART2);
        // System.out.println(day11.getWorryLevelByMonkeySummary());
        assertEquals(expectedResult, day11.getMonkeyInspectedItemsSummary());
        //System.out.println(day11.calculateMonkeyBusiness());
        //assertEquals(BigInteger.valueOf(2713310158L), day11.calculateMonkeyBusiness());
    }

    @Disabled
    @Test
    void testInputFilePart2() {
        String filename = "src/main/resources/2022day11input.txt";
        Day11 day11 = new Day11(filename, 10000, Day11.Day11Mode.PART2);
        System.out.println(day11.calculateMonkeyBusiness());
        assertEquals(BigInteger.valueOf(0L), day11.calculateMonkeyBusiness());
    }
}
