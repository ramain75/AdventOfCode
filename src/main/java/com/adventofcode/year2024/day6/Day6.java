package com.adventofcode.year2024.day6;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    enum Direction {
        NORTH('^'),
        EAST('>'),
        SOUTH('v'),
        WEST('<');

        private final char pointer;

        Direction(char pointer) {
            this.pointer = pointer;
        }

        public char getPointer() {
            return pointer;
        }

        /**
         *
         * @param facing
         * @return null if this isn't a facing char (^>v<) or the relevant Direction if it is
         */
        public static Direction getDirection(char facing) {
            for (Direction direction: Direction.values()) {
                if (direction.getPointer() == facing) {
                    return direction;
                }
            }
            return null;
        }

        public static Direction getNextDirection(Direction currentDirection) {
            return switch (currentDirection) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    }

    record Point(int x, int y) {}
    record Guard(Point point, Direction facing) {}

    // What is the layout of the lab, and does it have an obstruction?
    private final Map<Point, Boolean> labGrid = new HashMap<>();

    // Where has the guard been?
    private final Set<Point> distinctGuardPositions = new HashSet<>();

    // Where is the guard now?
    private Guard guard = null;

    // Size of the lab
    private final Point bottomRightPosition;

    public Day6(String input) {
        String[] rows = input.split("\\r?\\n");
        Point latestPositionMapped = null;

        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[y].length(); x++) {
                latestPositionMapped = new Point(x, y);
                labGrid.put(latestPositionMapped, rows[y].charAt(x) == '#');
                Direction possibleGuardDirection = Direction.getDirection(rows[y].charAt(x));
                if (possibleGuardDirection != null) {
                    if (guard != null) {
                        throw new RuntimeException("More than one guard found!");
                    }
                    guard = new Guard(latestPositionMapped, possibleGuardDirection);
                    distinctGuardPositions.add(latestPositionMapped);
                }
            }
        }

        bottomRightPosition = latestPositionMapped;
    }

    public int patrolLab() {
        if (labGrid.isEmpty() || bottomRightPosition == null || guard == null) {
            throw new RuntimeException("Lab layout or guard location not known, unable to continue");
        }

        int noOfStepsWithNoDistinctGuardPositionIncrease = 0;
        while (true) {
            moveOneStep(0);
            if (!labGrid.containsKey(guard.point)) {
                // no longer in the lab
                break;
            }

            if (!distinctGuardPositions.add(guard.point)) {
                noOfStepsWithNoDistinctGuardPositionIncrease++;
                if (noOfStepsWithNoDistinctGuardPositionIncrease > bottomRightPosition.x * bottomRightPosition.y) {
                    throw new RuntimeException("Guard unlikely to ever leave the lab, stuck in a loop");
                }
            }
        }

        return distinctGuardPositions.size();
    }

    private void moveOneStep(int directionsTried) {
        if (directionsTried == 4) {
            // We've already tried to move in 4 directions so no point trying again
            throw new RuntimeException("Stuck");
        }
        else {
            directionsTried++;
        }
        Point prospectiveNewPosition = null;
        switch (guard.facing) {
            case NORTH -> prospectiveNewPosition = new Point(guard.point.x, guard.point.y - 1);
            case EAST -> prospectiveNewPosition = new Point(guard.point.x + 1, guard.point.y);
            case SOUTH -> prospectiveNewPosition = new Point(guard.point.x, guard.point.y + 1);
            case WEST -> prospectiveNewPosition = new Point(guard.point.x - 1, guard.point.y);
        }
        if (labGrid.containsKey(prospectiveNewPosition) && labGrid.get(prospectiveNewPosition)) {
            // Obstacle in path so rotate right and try again
            guard = new Guard(guard.point, Direction.getNextDirection(guard.facing));
            moveOneStep(directionsTried);
        } else {
            // We can move but this might take us off the map
            guard = new Guard(prospectiveNewPosition, guard.facing);
        }
    }

    Point getBottomRightPosition() {
        return bottomRightPosition;
    }

    Guard getGuard() {
        return guard;
    }

    Set<Point> getDistinctGuardPositions() {
        return distinctGuardPositions.stream().collect(Collectors.toUnmodifiableSet());
    }

    Map<Point, Boolean> getLabGrid() {
        return Map.copyOf(labGrid);
    }
}
