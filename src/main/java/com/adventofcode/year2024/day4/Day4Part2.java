package com.adventofcode.year2024.day4;

public class Day4Part2 {

    public int countXmasInstancesInWordSearch(String input) {
        int xmasMatches = 0;
        String[] grid = input.split("\\r?\\n");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == 'A') {
                    // found a possible X-MAS
                    xmasMatches += findXmasFrom(grid, i, j) ? 1 : 0;
                }
            }
        }

        return xmasMatches;
    }

    private boolean findXmasFrom(String[] grid, int i, int j) {
        int count = 0;

        if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length() - 1) {
            return false;
        }

        // top left to bottom right and bottom right to top left
        int possible1 = grid[i-1].charAt(j-1);
        int possible2 = grid[i+1].charAt(j+1);

        if (
                (possible1 == 'M' && possible2 == 'S') ||
                (possible1 == 'S' && possible2 == 'M')
        ) {
            count++;
        }

        // top right to bottom left and bottom left to top right
        possible1 = grid[i-1].charAt(j+1);
        possible2 = grid[i+1].charAt(j-1);

        if (
                (possible1 == 'M' && possible2 == 'S') ||
                (possible1 == 'S' && possible2 == 'M')
        ) {
            count++;
        }

        return count == 2;
    }
}
