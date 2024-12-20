package com.adventofcode.year2024.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    @Test
    void testDay5WithSampleInput() {
        Day5 day5 = new Day5("""
                47|53
                97|13
                97|61
                97|47
                75|29
                61|13
                75|53
                29|13
                97|29
                53|29
                61|53
                97|53
                61|29
                47|13
                75|47
                97|75
                47|61
                75|61
                47|29
                75|13
                53|13
                
                75,47,61,53,29
                97,61,53,29,13
                75,29,13
                75,97,47,61,53
                61,13,29
                97,13,75,29,47""");
        assertEquals(143, day5.addMiddlePageNumbersForValidUpdates(), "Valid updates add up to an unexpected total");
        assertEquals(123, day5.fixAndAddMiddlePageNumbersForInvalidUpdates(), "Invalid updates, when reordered according to the rules, add up to an unexpected total");
    }
}
