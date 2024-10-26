package com.adventofcode.year2016.day8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwentySixteenDay8Test {

    TwentySixteenDay8 testBlankDisplay() {
        String expected = """
                .......
                .......
                .......""";
        TwentySixteenDay8 display = new TwentySixteenDay8(7, 3);
        assertEquals(expected, display.toString());
        return display;
    }

    TwentySixteenDay8 testRect3x2() {
        String expected = """
                ###....
                ###....
                .......""";
        TwentySixteenDay8 display = testBlankDisplay();
        display.rect(3, 2);
        assertEquals(expected, display.toString());
        return display;
    }

    TwentySixteenDay8 testRotateColumnX1by1() {
        String expected = """
                #.#....
                ###....
                .#.....""";
        TwentySixteenDay8 display = testRect3x2();
        display.rotateColumnX(1, 1);
        assertEquals(expected, display.toString());
        return display;
    }

    TwentySixteenDay8 testRotateRowY0by4() {
        String expected = """
                ....#.#
                ###....
                .#.....""";
        TwentySixteenDay8 display = testRotateColumnX1by1();
        display.rotateRowY(0, 4);
        assertEquals(expected, display.toString());
        return display;
    }

    @Test
    void testRotateColumnX1by1Again() {
        String expected = """
                .#..#.#
                #.#....
                .#.....""";
        TwentySixteenDay8 display = testRotateRowY0by4();
        display.rotateColumnX(1, 1);
        assertEquals(expected, display.toString());
    }

    @Test
    void testCountLitPixelsAfterIndividualInstructions() {
        int expected = 6;
        TwentySixteenDay8 display = testRotateRowY0by4();
        display.rotateColumnX(1, 1);
        assertEquals(expected, display.countLitPixels(), "Unexpected number of lit pixels");
    }

    @Test
    void testCountLitPixelsSampleData() {
        String input = """
                rect 3x2
                rotate column x=1 by 1
                rotate row y=0 by 4
                rotate column x=1 by 1""";
        String expected = """
                .#..#.#
                #.#....
                .#.....""";
        TwentySixteenDay8 display = new TwentySixteenDay8(7, 3);
        display.performInstructions(input);
        System.out.println(display.toString());
        assertEquals(expected, display.toString(), "Display layout not as expected");
        assertEquals(6, display.countLitPixels(), "Unexpected number of lit pixels");
    }

    @Test
    void testCountLitPixelsPuzzleInput() {
        String expected = """
                ###..#..#.###..#..#..##..####..##..####..###.#....
                #..#.#..#.#..#.#..#.#..#.#....#..#.#......#..#....
                #..#.#..#.#..#.#..#.#....###..#..#.###....#..#....
                ###..#..#.###..#..#.#....#....#..#.#......#..#....
                #.#..#..#.#.#..#..#.#..#.#....#..#.#......#..#....
                #..#..##..#..#..##...##..####..##..####..###.####.""";
        TwentySixteenDay8 display = new TwentySixteenDay8(50, 6);
        display.performInstructions(puzzleInput);
        assertEquals(expected, display.toString(), "Display layout not as expected");
        assertEquals(121, display.countLitPixels(), "Unexpected number of lit pixels");
    }

    final static String puzzleInput = """
            rect 1x1
            rotate row y=0 by 10
            rect 1x1
            rotate row y=0 by 10
            rect 1x1
            rotate row y=0 by 5
            rect 1x1
            rotate row y=0 by 3
            rect 2x1
            rotate row y=0 by 4
            rect 1x1
            rotate row y=0 by 3
            rect 1x1
            rotate row y=0 by 2
            rect 1x1
            rotate row y=0 by 3
            rect 2x1
            rotate row y=0 by 2
            rect 1x1
            rotate row y=0 by 3
            rect 2x1
            rotate row y=0 by 5
            rotate column x=0 by 1
            rect 4x1
            rotate row y=1 by 12
            rotate row y=0 by 10
            rotate column x=0 by 1
            rect 9x1
            rotate column x=7 by 1
            rotate row y=1 by 3
            rotate row y=0 by 2
            rect 1x2
            rotate row y=1 by 3
            rotate row y=0 by 1
            rect 1x3
            rotate column x=35 by 1
            rotate column x=5 by 2
            rotate row y=2 by 5
            rotate row y=1 by 5
            rotate row y=0 by 2
            rect 1x3
            rotate row y=2 by 8
            rotate row y=1 by 10
            rotate row y=0 by 5
            rotate column x=5 by 1
            rotate column x=0 by 1
            rect 6x1
            rotate row y=2 by 7
            rotate row y=0 by 5
            rotate column x=0 by 1
            rect 4x1
            rotate column x=40 by 2
            rotate row y=2 by 10
            rotate row y=0 by 12
            rotate column x=5 by 1
            rotate column x=0 by 1
            rect 9x1
            rotate column x=43 by 1
            rotate column x=40 by 2
            rotate column x=38 by 1
            rotate column x=15 by 1
            rotate row y=3 by 35
            rotate row y=2 by 35
            rotate row y=1 by 32
            rotate row y=0 by 40
            rotate column x=32 by 1
            rotate column x=29 by 1
            rotate column x=27 by 1
            rotate column x=25 by 1
            rotate column x=23 by 2
            rotate column x=22 by 1
            rotate column x=21 by 3
            rotate column x=20 by 1
            rotate column x=18 by 3
            rotate column x=17 by 1
            rotate column x=15 by 1
            rotate column x=14 by 1
            rotate column x=12 by 1
            rotate column x=11 by 3
            rotate column x=10 by 1
            rotate column x=9 by 1
            rotate column x=8 by 2
            rotate column x=7 by 1
            rotate column x=4 by 1
            rotate column x=3 by 1
            rotate column x=2 by 1
            rotate column x=0 by 1
            rect 34x1
            rotate column x=44 by 1
            rotate column x=24 by 1
            rotate column x=19 by 1
            rotate row y=1 by 8
            rotate row y=0 by 10
            rotate column x=8 by 1
            rotate column x=7 by 1
            rotate column x=6 by 1
            rotate column x=5 by 2
            rotate column x=3 by 1
            rotate column x=2 by 1
            rotate column x=1 by 1
            rotate column x=0 by 1
            rect 9x1
            rotate row y=0 by 40
            rotate column x=43 by 1
            rotate row y=4 by 10
            rotate row y=3 by 10
            rotate row y=2 by 5
            rotate row y=1 by 10
            rotate row y=0 by 15
            rotate column x=7 by 2
            rotate column x=6 by 3
            rotate column x=5 by 2
            rotate column x=3 by 2
            rotate column x=2 by 4
            rotate column x=0 by 2
            rect 9x2
            rotate row y=3 by 47
            rotate row y=0 by 10
            rotate column x=42 by 3
            rotate column x=39 by 4
            rotate column x=34 by 3
            rotate column x=32 by 3
            rotate column x=29 by 3
            rotate column x=22 by 3
            rotate column x=19 by 3
            rotate column x=14 by 4
            rotate column x=4 by 3
            rotate row y=4 by 3
            rotate row y=3 by 8
            rotate row y=1 by 5
            rotate column x=2 by 3
            rotate column x=1 by 3
            rotate column x=0 by 2
            rect 3x2
            rotate row y=4 by 8
            rotate column x=45 by 1
            rotate column x=40 by 5
            rotate column x=26 by 3
            rotate column x=25 by 5
            rotate column x=15 by 5
            rotate column x=10 by 5
            rotate column x=7 by 5
            rotate row y=5 by 35
            rotate row y=4 by 42
            rotate row y=2 by 5
            rotate row y=1 by 20
            rotate row y=0 by 45
            rotate column x=48 by 5
            rotate column x=47 by 5
            rotate column x=46 by 5
            rotate column x=43 by 5
            rotate column x=41 by 5
            rotate column x=38 by 5
            rotate column x=37 by 5
            rotate column x=36 by 5
            rotate column x=33 by 1
            rotate column x=32 by 5
            rotate column x=31 by 5
            rotate column x=30 by 1
            rotate column x=28 by 5
            rotate column x=27 by 5
            rotate column x=26 by 5
            rotate column x=23 by 1
            rotate column x=22 by 5
            rotate column x=21 by 5
            rotate column x=20 by 1
            rotate column x=17 by 5
            rotate column x=16 by 5
            rotate column x=13 by 1
            rotate column x=12 by 3
            rotate column x=7 by 5
            rotate column x=6 by 5
            rotate column x=3 by 1
            rotate column x=2 by 3""";
}
