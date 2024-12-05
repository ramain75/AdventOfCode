package com.adventofcode.year2024.day4;

public class Day4Part1 {

    public int countXmasInstancesInWordSearch(String input) {
        int xmasMatches = 0;
        String[] grid = input.split("\\r?\\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == 'X') {
                    // found a possible start of a word
                    xmasMatches += findXmasFrom(grid, i, j);
                }
            }
        }

        return xmasMatches;
    }

    private int findXmasFrom(String[] grid, int i, int j) {
        int count = 0;

        // left to right
        int right = grid[i].indexOf("XMAS", j);
        if (right == j) {
            count++;
        }

        // right to left
        if (j >= 3) {
            int left = grid[i].indexOf("SAMX", j - 3);
            if (left == j - 3) {
                count++;
            }
        }

        // bottom to top
        if (i >= 3) {
            int possibleM = grid[i-1].charAt(j);
            int possibleA = grid[i-2].charAt(j);
            int possibleS = grid[i-3].charAt(j);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        // top to bottom
        if (grid.length > i + 3) {
            int possibleM = grid[i+1].charAt(j);
            int possibleA = grid[i+2].charAt(j);
            int possibleS = grid[i+3].charAt(j);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        // bottom left to top right
        if (i >= 3 && grid[0].length() > j + 3) {
            int possibleM = grid[i-1].charAt(j+1);
            int possibleA = grid[i-2].charAt(j+2);
            int possibleS = grid[i-3].charAt(j+3);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        // top left to bottom right
        if (grid.length > i + 3 && grid[0].length() > j + 3) {
            int possibleM = grid[i+1].charAt(j+1);
            int possibleA = grid[i+2].charAt(j+2);
            int possibleS = grid[i+3].charAt(j+3);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        // top right to bottom left
        if (grid.length > i + 3 && j >= 3) {
            int possibleM = grid[i+1].charAt(j-1);
            int possibleA = grid[i+2].charAt(j-2);
            int possibleS = grid[i+3].charAt(j-3);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        // bottom right to top left
        if (i >= 3 && j >= 3) {
            int possibleM = grid[i-1].charAt(j-1);
            int possibleA = grid[i-2].charAt(j-2);
            int possibleS = grid[i-3].charAt(j-3);

            if (possibleM == 'M' && possibleA == 'A' && possibleS == 'S') {
                count++;
            }
        }

        return count;
    }
}
