package com.adventofcode.year2016.day7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TwentySixteenDay7Test {
    @ParameterizedTest
    @ValueSource(strings = {"abba[mnop]qrst", "ioxxoj[asdfgh]zxcvbn"})
    void testHasIpABBATrue(String input) {
        assertTrue(TwentySixteenDay7.hasIpAbba.test(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd[bddb]xyyx", "aaaa[qwer]tyui"})
    void testHasIpABBAFalse(String input) {
        assertFalse(TwentySixteenDay7.hasIpAbba.test(input));
    }

    @Test
    void testcountIPsSupportingTLSSampleInput() {
        String sampleInput = """
            abba[mnop]qrst
            abcd[bddb]xyyx
            aaaa[qwer]tyui
            ioxxoj[asdfgh]zxcvbn""";

        assertEquals(2, TwentySixteenDay7.countIPsSupportingTLS(sampleInput), "Count of IPs supporting TLS is not as expected");
    }

    @Test
    void testcountIPsSupportingTLSPuzzleInput() throws IOException {
        String puzzleInput = Files.readString(Path.of(this.getClass().getClassLoader().getResource("2016day7input.txt").getFile()));
        assertEquals(118, TwentySixteenDay7.countIPsSupportingTLS(puzzleInput), "Count of IPs supporting TLS is not as expected");
    }
}