package com.adventofcode.year2016.day1;

public class TwentySixteenDay1 {

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    record Point(int x, int y) {
    }

    public static int getDistanceFromGroundZero(String input) {
        Point currPosition = new Point(0, 0);
        Direction pointing = Direction.NORTH;
        final String[] split = input.split(", ");

        for (String move:
             split) {
            String directionStr = move.substring(0,1);
            String distanceStr = move.substring(1);
            int distance = Integer.parseInt(distanceStr);

            System.out.printf("Moving %s %s blocks\n", directionStr, distanceStr);

            if (directionStr.equals("R")) {
                pointing = moveRight(pointing);
            } else if (directionStr.equals("L")) {
                pointing = moveLeft(pointing);
            } else {
                throw new RuntimeException("Invalid move");
            }
            currPosition = move(currPosition, pointing, distance);

            System.out.printf("New location is (%d, %d). Now facing %s.\n", currPosition.x(), currPosition.y(), pointing);
        }

        int distanceFromGroundZero = distanceFromGroundZero(currPosition);
        System.out.printf("Shortest path is %d blocks from the start.\n", distanceFromGroundZero);
        return distanceFromGroundZero;
    }

    private static int distanceFromGroundZero(Point currPosition) {
        return Math.abs(currPosition.x + currPosition.y);
    }

    static Direction moveRight(Direction from) {
        return switch(from) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
        };
    }

    static Direction moveLeft(Direction from) {
        return switch(from) {
            case NORTH -> Direction.WEST;
            case EAST -> Direction.NORTH;
            case SOUTH -> Direction.EAST;
            case WEST -> Direction.SOUTH;
        };
    }

    static Point move(Point start, Direction direction, int noOfBlocks) {
        return switch (direction) {
            case NORTH -> new Point(start.x(), start.y() + noOfBlocks);
            case EAST -> new Point(start.x() + noOfBlocks, start.y());
            case SOUTH -> new Point(start.x(), start.y() - noOfBlocks);
            case WEST -> new Point(start.x() - noOfBlocks, start.y());
        };
    }
}
