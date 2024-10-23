package com.adventofcode.year2016.day5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwentySixteenDay5Test {

    @Test
    void testFindFirstMD5Starting00000() {
        String doorId = "abc";
        TwentySixteenDay5.MD5HashResult matchingMD5 = TwentySixteenDay5.findFirstMD5Starting00000(doorId, 0);
        assertEquals(doorId, matchingMD5.doorId(), "Door ID for first match was not as expected");
        assertEquals(3231929, matchingMD5.counter(), "Counter for first match was not as expected");
        assertTrue(matchingMD5.result() != null && matchingMD5.result().startsWith("000001"), "Result for first match was not as expected");
    }

    @Test
    void testFindMD5BasedPasswordForDoorIdSampleData() {
        String doorId = "abc";
        assertEquals("18f47a30", TwentySixteenDay5.findMD5BasedPasswordForDoorId(doorId), "MD5 based password was not as expected");
    }

    @Test
    void testFindMD5BasedPasswordForDoorIdPuzzleInput() {
        String doorId = "reyedfim";
        assertEquals("f97c354d", TwentySixteenDay5.findMD5BasedPasswordForDoorId(doorId), "MD5 based password was not as expected");
    }

    @Test
    void testFindMD5BasedMoreInspiredPasswordForDoorIdPSampleData() {
        String doorId = "abc";
        assertEquals("05ace8e3", TwentySixteenDay5.findMD5BasedMoreInspiredPasswordForDoorId(doorId), "MD5 based password was not as expected");
    }

    @Test
    void testFindMD5BasedMoreInspiredPasswordForDoorIdPuzzleInput() {
        String doorId = "reyedfim";
        assertEquals("863dde27", TwentySixteenDay5.findMD5BasedMoreInspiredPasswordForDoorId(doorId), "MD5 based password was not as expected");
    }

}
