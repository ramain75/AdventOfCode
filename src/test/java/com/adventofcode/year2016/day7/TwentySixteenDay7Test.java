package com.adventofcode.year2016.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwentySixteenDay7Test {
    @ParameterizedTest
    @ValueSource(strings = { "abba[mnop]qrst", "ioxxoj[asdfgh]zxcvbn" })
    void testHasIpABBATrue(String input) {
        assertTrue(TwentySixteenDay7.hasIpABBA(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "abcd[bddb]xyyx", "aaaa[qwer]tyui" })
    void testHasIpABBAFalse(String input) {
        assertFalse(TwentySixteenDay7.hasIpABBA(input));
    }
}
