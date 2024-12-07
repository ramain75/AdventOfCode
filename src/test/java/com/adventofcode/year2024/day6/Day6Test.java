package com.adventofcode.year2024.day6;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Day6Test {

    @Test
    void testDay6WithSampleInput() {
        String input = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...""";
        Day6 day6 = new Day6(input);
        Map<Day6.Point, Character> labGrid = day6.getLabGrid();
        assertEquals(100, labGrid.size());
        assertEquals('^', labGrid.get(new Day6.Point(4, 6)));
        assertEquals('#', labGrid.get(new Day6.Point(6, 9)));
        assertEquals('.', labGrid.get(new Day6.Point(9, 9)));
        Set<Day6.Point> distinctGuardPositions = day6.getDistinctGuardPositionKeys();
        assertEquals(1, distinctGuardPositions.size());
        assertEquals(Day6.Direction.NORTH, day6.getDistinctGuardPositionValue(new Day6.Point(4, 6)).stream().findFirst().orElseThrow());
        assertEquals(new Day6.Guard(new Day6.Point(4, 6), Day6.Direction.NORTH), day6.getGuard());
        assertEquals(new Day6.Point(9, 9), day6.getBottomRightPosition());

        assertEquals(41, day6.patrolLab());
        System.out.println(day6.getLabGridString());

        List<Day6.Point> stuckInALoopPoints = day6.getStuckInALoopPoints();
        assertEquals(6, stuckInALoopPoints.size());
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(3, 6)), "Expected option one at Point(3, 6)");
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(6, 7)), "Expected option two at Point(6, 7)");
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(7, 7)), "Expected option three at Point(7, 7)");
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(1, 8)), "Expected option four at Point(1, 8)");
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(3, 8)), "Expected option five at Point(3, 8)");
        assertTrue(stuckInALoopPoints.contains(new Day6.Point(7, 9)), "Expected option six at Point(7, 9)");
    }
}
