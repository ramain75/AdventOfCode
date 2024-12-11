package com.adventofcode.year2024.day7;

import java.util.Arrays;

public class Day7 {

    enum Operator {
        ADD('+'),
        MULTIPLY('*');

        private final char symbol;

        Operator(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }
    }

    public long getTotalCalibrationResult(String input) {
        long result = 0L;

        String[] calibrationResults = input.split("\\r?\\n");
        for (String calibrationResult : calibrationResults) {
            System.out.printf("Processing %s%n", calibrationResult);
            if (isValidCalibrationEquation(calibrationResult)) {
                String[] resultAndValues = calibrationResult.split(":\\s");

                result += Long.parseLong(resultAndValues[0]);

                System.out.printf("Valid calibration equation found, adding %s to result, which is now %d%n", resultAndValues[0], result);

            }

        }

        return result;
    }

    boolean isValidCalibrationEquation(String input) {
        String[] resultAndValues = input.split(":\\s");
        if (resultAndValues.length != 2) {
            throw new RuntimeException("Invalid input: " + input);
        }

        long result = Long.parseLong(resultAndValues[0]);
        int[] operands = Arrays.stream(resultAndValues[1].split("\\s")).mapToInt(Integer::parseInt).toArray();

        // TODO: try + and * combinations between each operands to see if the result is correct for any of them
        Operator[][] operators = getOperatorCombinations(operands.length);

        // for each operator combination, loop through the operands (e.g. 1 8 7) applying the operators (e.g. + +)
            // if the result matches the expected result, return true
        // if there are no matches, return false

        // loop through the arrays (an operator combination)
        for (int operatorCombo = 0; operatorCombo < operators.length; operatorCombo++) {
            // loop through the pairs of operands and their corresponding operator and perform the action
            long comboResult = operands[0];
            for (int operandElem2 = 1, operatorElem = operators[operatorCombo].length - 1;
                 operandElem2 < operands.length && operatorElem >= 0;
                 operandElem2++, operatorElem--) {

                if (operators[operatorCombo][operatorElem] == Operator.ADD) {
                    comboResult += operands[operandElem2];
                } else {
                    comboResult *= operands[operandElem2];
                }
            }

            if (result == comboResult) {
                return true;
            }
        }

        // TODO: Return true if any correct, otherwise false
        return false;
    }

    // a + b (0-1)
    // a * b
    // a + b + c (0-3)
    // a + b * c
    // a * b + c
    // a * b * c
    Operator[][] getOperatorCombinations(int noOfOperands) {
        int elems = noOfOperands - 1;
        int arrs = (int) Math.pow(2, noOfOperands - 1);

        Operator[][] operators = new Operator[arrs][elems];
        // loop through the arrays
        for (int i = 0; i < arrs; i++) {
            // loop through the elements for one array, left to right
            for (int j = elems-1; j >= 0; j--) {
                boolean isBitSet = (i & (1 << j)) > 0; // 0 or 1
                operators[i][j] = isBitSet ? Operator.MULTIPLY : Operator.ADD;

                //System.out.print(operators[i][j].getSymbol());
            }
            //System.out.println();
        }

        return operators;
    }

}
