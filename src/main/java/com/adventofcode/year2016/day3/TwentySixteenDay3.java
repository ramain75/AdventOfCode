package com.adventofcode.year2016.day3;

import java.util.Arrays;

public class TwentySixteenDay3 {
    public static int determineNumberOfPossibleTriangles(String input) {
        int possibleTriangles = 0;

        for (String triangle:
            input.split(System.getProperty("line.separator"))) {
            int[] sides = Arrays.stream(triangle.trim().split("\s+"))
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();

            if (sides.length != 3) {
                throw new RuntimeException("Not a triangle, wrong number of sides");
            }

            System.out.println(Arrays.toString(sides));

            if (sides[0] + sides[1] > sides[2]) {
                possibleTriangles++;
                System.out.println("Valid triangle");
            } else {
                System.out.println("Invalid triangle");
            }
        }

        System.out.printf("Number of valid triangles: %d", possibleTriangles);
        return possibleTriangles;
    }

    public static int determineNumberOfPossibleVerticalTriangles(String input) {
        int possibleTriangles = 0;
        int rowNo = -1;
        int[][] triangleGroups = new int[3][];

        for (String triangle:
                input.split(System.getProperty("line.separator"))) {
            rowNo++;
            int[] row = Arrays.stream(triangle.trim().split("\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (row.length != 3) {
                throw new RuntimeException("Wrong row length");
            }

            System.out.println("row: " + Arrays.toString(row));
            triangleGroups[rowNo] = row;

            if (rowNo == 2) {
                // Process each column
                for (int i = 0; i < 3; i++) {
                    int[] sides = Arrays.stream(
                            new int[] { triangleGroups[0][i], triangleGroups[1][i], triangleGroups[2][i] }
                    )
                            .sorted()
                            .toArray();
                    System.out.println("Sides: " + Arrays.toString(sides));

                    if (sides[0] + sides[1] > sides[2]) {
                        possibleTriangles++;
                        System.out.println("Valid triangle");
                    } else {
                        System.out.println("Invalid triangle");
                    }
                }

                rowNo = -1;
                triangleGroups = new int[3][];
            }
        }

        System.out.printf("Number of valid triangles: %d", possibleTriangles);
        return possibleTriangles;
    }
}
