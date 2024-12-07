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

    // What is the layout of the lab, does it have an obstruction (#), or has it been patrolled (^|-+)?
    private final Map<Point, Character> labGrid = new HashMap<>();

    // Where has the guard been?
    private final Map<Point, Set<Direction>> distinctGuardPositions = new HashMap<>();

    // Where is the guard now?
    private Guard guard = null;

    // Size of the lab
    private final Point bottomRightPosition;

    private final List<Point> stuckInALoopPoints = new ArrayList<>();

    public Day6(String input) {
        String[] rows = input.split("\\r?\\n");
        Point latestPositionMapped = null;

        for (int y = 0; y < rows.length; y++) {
            for (int x = 0; x < rows[y].length(); x++) {
                latestPositionMapped = new Point(x, y);
                labGrid.put(latestPositionMapped, rows[y].charAt(x));
                Direction possibleGuardDirection = Direction.getDirection(rows[y].charAt(x));
                if (possibleGuardDirection != null) {
                    if (guard != null) {
                        throw new RuntimeException("More than one guard found!");
                    }
                    guard = new Guard(latestPositionMapped, possibleGuardDirection);
                    Set<Direction> directionSet = distinctGuardPositions.getOrDefault(latestPositionMapped, new HashSet<>());
                    directionSet.add(possibleGuardDirection);
                    distinctGuardPositions.put(latestPositionMapped, directionSet);
                }
            }
        }

        bottomRightPosition = latestPositionMapped;
    }

    public int patrolLab() {
        if (labGrid.isEmpty() || bottomRightPosition == null || guard == null || !stuckInALoopPoints.isEmpty()) {
            throw new RuntimeException("Lab layout or guard location not known, unable to continue");
        }

        while (true) {
            moveOneStep(0);
            if (!labGrid.containsKey(guard.point)) {
                // no longer in the lab
                break;
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
        Point prospectiveNewPosition = getPositionFromCurrent(guard.point, guard.facing);
        if (labGrid.containsKey(prospectiveNewPosition) && labGrid.get(prospectiveNewPosition).charValue() == '#') {
            // Obstacle in path so rotate right and try again
            labGrid.put(guard.point, '+');
            guard = new Guard(guard.point, Direction.getNextDirection(guard.facing));
            moveOneStep(directionsTried);
        } else {
            // We can move but could be off the map
            Set<Direction> directionSet = distinctGuardPositions.getOrDefault(guard.point, new HashSet<>());
            char c = labGrid.get(guard.point);
            // if you turn right from guard existing position, have you been there before in that direction
            // Or, looking right, if i keep going, do i find a point that has the same direction in it
            // before i either hit an obstacle or the edge of the lab?
            if (directionSet.contains(Direction.getNextDirection(guard.facing)) ||
                    isStuckInLoopInDirection(guard.point, Direction.getNextDirection(guard.facing))) {
                stuckInALoopPoints.add(prospectiveNewPosition);
            }
            if (c == '.' || c == '^') {
                c = (guard.facing == Direction.NORTH || guard.facing == Direction.SOUTH) ? '|' : '-';
            } else if (c == '|') {
                if (guard.facing == Direction.EAST || guard.facing == Direction.WEST) {
                    c = '+';
                }
            } else if (c == '-') {
                if (guard.facing == Direction.NORTH || guard.facing == Direction.SOUTH) {
                    c = '+';
                }
            }
            labGrid.put(guard.point, c);
            directionSet.add(guard.facing);
            distinctGuardPositions.put(guard.point, directionSet);
            guard = new Guard(prospectiveNewPosition, guard.facing);
        }
    }

    /**
     * This must be flawed as it gets the wrong answer for the puzzle input. I must be missing a condition. All I could
     * think of is if it turned right at each # rather than dropping out, but that caused a stack overflow?
     * @param point
     * @param direction
     * @return
     */
    private boolean isStuckInLoopInDirection(Point point, Direction direction) {
        Point previousPosition = point;
        Point positionFromCurrent = point;
        while (true) {
            previousPosition = positionFromCurrent;
            positionFromCurrent = getPositionFromCurrent(positionFromCurrent, direction);
            if (!labGrid.containsKey(positionFromCurrent)) {
                // off the map
                break;
            }
            if (labGrid.get(positionFromCurrent) == '#') {
                // no match, hit an obstacle
                break;
            }
            Set<Direction> directionSet = distinctGuardPositions.getOrDefault(positionFromCurrent, new HashSet<>());
            if (directionSet.contains(direction)) {
                return true;
            }
        }

        return false;
    }

    private Point getPositionFromCurrent(Point current, Direction facing) {
        return switch (facing) {
            case NORTH -> new Point(current.x, current.y - 1);
            case EAST -> new Point(current.x + 1, current.y);
            case SOUTH -> new Point(current.x, current.y + 1);
            case WEST -> new Point(current.x - 1, current.y);
        };
    }

    Point getBottomRightPosition() {
        return bottomRightPosition;
    }

    Guard getGuard() {
        return guard;
    }

    Set<Point> getDistinctGuardPositionKeys() {
        return distinctGuardPositions.keySet();
    }

    Set<Direction> getDistinctGuardPositionValue(Point point) {
        return Set.copyOf(distinctGuardPositions.get(point));
    }

    Map<Point, Character> getLabGrid() {
        return Map.copyOf(labGrid);
    }

    String getLabGridString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y <= bottomRightPosition.y; y++) {
            for (int x = 0; x <= bottomRightPosition.x; x++) {
                sb.append(labGrid.get(new Point(x, y)));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    List<Point> getStuckInALoopPoints() {
        return stuckInALoopPoints;
    }
}
