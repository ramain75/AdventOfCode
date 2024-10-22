package com.adventofcode.year2016.day1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwentySixteenDay1Test {

    @Test
    void testPart1Scenario1() {
        String input = "R2, L3";
        assertEquals(5, TwentySixteenDay1.getDistanceToEasterBunnyHQPart1(input), "Expected distance from ground zero is wrong");
    }

    @Test
    void testPart1Scenario2() {
        String input = "R2, R2, R2";
        assertEquals(2, TwentySixteenDay1.getDistanceToEasterBunnyHQPart1(input), "Expected distance from ground zero is wrong");
    }

    @Test
    void testPart1Scenario3() {
        String input = "R5, L5, R5, R3";
        assertEquals(12, TwentySixteenDay1.getDistanceToEasterBunnyHQPart1(input), "Expected distance from ground zero is wrong");
    }

    @Test
    void testPart1RealData() {
        String input = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1";
        assertEquals(252, TwentySixteenDay1.getDistanceToEasterBunnyHQPart1(input), "Expected distance from ground zero is wrong");
    }

    @Test
    void testPart2Scenario1() {
        String input = "R8, R4, R4, R8";
        assertEquals(4, TwentySixteenDay1.getDistanceToEasterBunnyHQPart2(input), "Expected distance from ground zero is wrong");
    }

    @Test
    void testPart2RealData() {
        String input = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1";
        assertEquals(143, TwentySixteenDay1.getDistanceToEasterBunnyHQPart2(input), "Expected distance from ground zero is wrong");
    }
}
