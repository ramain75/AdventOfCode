package com.adventofcode.year2024.day2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day2 {

    private boolean isSafeReport(int[] levels) {
        boolean safeIncreasing = true;
        boolean safeDecreasing = true;
        for (int i = 1; i < levels.length; i++) {
            // check that it increases by at least one and at most three
            if (safeIncreasing && (levels[i-1] >= levels[i] || levels[i-1]+3 < levels[i])) {
                safeIncreasing = false;
                if (!safeDecreasing) {
                    break;
                }
            }

            // check that it decreases by at least one and at most three
            if (safeDecreasing && (levels[i-1] <= levels[i] || levels[i-1]-3 > levels[i])) {
                safeDecreasing = false;
                if (!safeIncreasing) {
                    break;
                }
            }
        }

        return safeDecreasing || safeIncreasing;
    }

    public int getNumSafeReports(String input, boolean applyDampener) {
        int numOfSafeReports = 0;

        outer: for (String s : input.split("\\r?\\n")) {
            System.out.println(s);
            int[] levels = Arrays.stream(s.trim().split("\\s")).mapToInt(Integer::parseInt).toArray();
            if (isSafeReport(levels)) {
                System.out.println("Matched without dampener: " + Arrays.toString(levels));
                numOfSafeReports++;
                continue outer;
            }

            if (applyDampener) {
                for (int levelToSkip = 0; levelToSkip < levels.length; levelToSkip++) {
                    int[] start = Arrays.copyOfRange(levels, 0, levelToSkip);
                    int[] end = levelToSkip < levels.length - 1 ? Arrays.copyOfRange(levels, levelToSkip+1, levels.length) : new int[0];
                    int[] combination = IntStream.concat(Arrays.stream(start), Arrays.stream(end)).toArray();
                    if (isSafeReport(combination)) {
                        System.out.println("Matched with dampener: " + Arrays.toString(levels) + ", matching combination: " + Arrays.toString(combination));
                        numOfSafeReports++;
                        continue outer;
                    }
                }
            }
        }

        return numOfSafeReports;
    }
}
