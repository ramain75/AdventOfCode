package com.adventofcode.year2016.day1;

import java.util.HashSet;
import java.util.Set;

public class TwentySixteenDay1 {

    enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    record Point(int x, int y) {
    }

    public static int getDistanceToEasterBunnyHQPart1(String input) {
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

            System.out.printf("New location is (%d, %d). Now facing %s.\n", currPosition.x, currPosition.y, pointing);
        }

        int distanceFromGroundZero = distanceFromGroundZero(currPosition);
        System.out.printf("Shortest path is %d blocks from the start.\n", distanceFromGroundZero);
        return distanceFromGroundZero;
    }

    public static int getDistanceToEasterBunnyHQPart2(String input) {
        Point currPosition = new Point(0, 0);
        Set<Point> visitedLocations = new HashSet<>();
        visitedLocations.add(currPosition);
        Direction pointing = Direction.NORTH;
        boolean foundEasterBunnyHQ = false;
        final String[] split = input.split(", ");

        first:
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
            while (distance > 0) {
                currPosition = moveOne(currPosition, pointing);
                System.out.printf("New location is (%d, %d). Now facing %s.\n", currPosition.x, currPosition.y, pointing);

                if (visitedLocations.contains(currPosition)) {
                    foundEasterBunnyHQ = true;
                    System.out.printf("Found Easter Bunny HQ location at (%d, %d). Stopping processing directions.\n", currPosition.x, currPosition.y);
                    break first;
                }
                visitedLocations.add(currPosition);
                distance--;
            }
        }

        if (!foundEasterBunnyHQ) {
            throw new RuntimeException("Unable to find Easter Bunny HQ. No location visited twice.");
        }
        int distanceFromGroundZero = distanceFromGroundZero(currPosition);
        System.out.printf("Shortest path is %d blocks from the start.\n", distanceFromGroundZero);
        return distanceFromGroundZero;
    }

    private static int distanceFromGroundZero(Point currPosition) {
        return Math.abs(currPosition.x) + Math.abs(currPosition.y);
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
            case NORTH -> new Point(start.x, start.y + noOfBlocks);
            case EAST -> new Point(start.x + noOfBlocks, start.y);
            case SOUTH -> new Point(start.x, start.y - noOfBlocks);
            case WEST -> new Point(start.x - noOfBlocks, start.y);
        };
    }

    static Point moveOne(Point start, Direction direction) {
        return move(start, direction, 1);
    }
}
