package com.adventofcode.year2024.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    @Test
    void testDay9SampleInput() {
        String input = "2333133121414131402";
        Day9 day9 = new Day9(input);
        assertEquals("00...111...2...333.44.5555.6666.777.888899", day9.getDiskBlockLayout());
        day9.compactHDDPart1();
        assertEquals("0099811188827773336446555566..............", day9.getDiskBlockLayout());
        long checksum = day9.getFilesystemChecksum();
        assertEquals(1928L, checksum);
    }

    @Test
    void testDay9PuzzleInput() {
        String input = """
                HIDDEN""";
        Day9 day9 = new Day9(input);
        day9.compactHDDPart1();
        long checksum = day9.getFilesystemChecksum();
        //assertEquals(-1L, checksum);
    }

    @Test
    void testDay9Part2SampleInput() {
        String input = "2333133121414131402";
        Day9 day9 = new Day9(input);
        assertEquals("00...111...2...333.44.5555.6666.777.888899", day9.getDiskBlockLayout());
        day9.compactHDDPart2();
        assertEquals("00992111777.44.333....5555.6666.....8888..", day9.getDiskBlockLayout());
        long checksum = day9.getFilesystemChecksum();
        assertEquals(2858L, checksum);
    }

    @Test
    void testDay9Part2PuzzleInput() {
        String input = """
                HIDDEN""";
        Day9 day9 = new Day9(input);
        day9.compactHDDPart2();
        long checksum = day9.getFilesystemChecksum();
        //assertEquals(-1L, checksum);
    }
}
