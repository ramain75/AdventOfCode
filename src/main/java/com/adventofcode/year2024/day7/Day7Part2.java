package com.adventofcode.year2024.day7;

import java.util.Arrays;

public class Day7Part2 {

    /**
     *
     * @param input String to parse into calibration equations (one per line)
     * @param noOfOperatorsToUse 2 for just + and *, 3 for +, * and ||
     * @return sum of the valid calibration results
     */
    public long getTotalCalibrationResult(String input, int noOfOperatorsToUse) {
        // validation
        if (noOfOperatorsToUse < 2 || noOfOperatorsToUse > 3) {
            throw new IllegalArgumentException("noOfOperatorsToUse must be 2 (part 1) or 3 (part 2)");
        }

        long result = 0L;

        String[] calibrationResults = input.split("\\r?\\n");
        for (String calibrationResult : calibrationResults) {
            System.out.printf("Processing %s%n", calibrationResult);
            long equationResult = isValidCalibrationEquation(calibrationResult, noOfOperatorsToUse);
            if (equationResult > 0) {
                result += equationResult;

                System.out.printf("Valid calibration equation found, adding %s to result, which is now %d%n", equationResult, result);

            }

        }

        return result;
    }

    /**
     *
     * @param input a single line equation to evaluate e.g. "156: 15 6"
     * @param noOfOperatorsToUse 2 for just + and *, 3 for +, * and ||
     * @return 0 if invalid, result of the equation if valid
     */
    long isValidCalibrationEquation(String input, int noOfOperatorsToUse) {
        String[] resultAndValues = input.split(":\\s");
        if (resultAndValues.length != 2) {
            throw new RuntimeException("Invalid input: " + input);
        }

        long result = Long.parseLong(resultAndValues[0]);
        int[] operands = Arrays.stream(resultAndValues[1].split("\\s")).mapToInt(Integer::parseInt).toArray();

        int noOfOperands = operands.length;
        // How many elements should each array have - doesn't change based on number of different operators
        int elems = noOfOperands - 1;
        // how many arrays do we need to represent all combinations ( +, *, pow(2) or +, *, || pow(3)
        int arrs = (int) Math.pow(noOfOperatorsToUse, noOfOperands - 1);

        System.out.printf("Operands: %d, elems: %d, arrs: %d%n", noOfOperands, elems, arrs);

        //  0,  1,  2       0, 1, 2,
        // 10, 11, 12       3, 4, 5,
        // 20, 21, 22       6, 7, 8
        for (int i = 0; i < arrs; i++) {
            long total = operands[0];
            String representation = String.format("%" + elems + "s", Integer.toString(i, noOfOperatorsToUse)).replace(' ', '0');
            //System.out.println(representation);

            // go through each 'digit', 0: +, 1: *, 2: ||
            for (int j = 0; j < representation.length(); j++) {
                char baseNDigit = representation.charAt(j);
                if (baseNDigit == '0') {
                    total += operands[j+1];
                }
                else if (baseNDigit == '1') {
                    total *= operands[j+1];
                }
                else if (baseNDigit == '2') {
                    total = Long.parseLong("" + String.valueOf(total) + String.valueOf(operands[j+1]));
                }
            }

            if (total == result) {
                return result;
            }
        }


        return 0;
    }

}
