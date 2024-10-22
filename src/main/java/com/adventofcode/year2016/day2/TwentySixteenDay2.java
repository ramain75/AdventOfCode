package com.adventofcode.year2016.day2;

public class TwentySixteenDay2 {

    public static String determineBathroomCodeOnNumericKeypad(String input) {
        int currKey = 5;
        int digitNo = 0;
        String code = "";

        String[] lines = input.split("\\r?\\n");
        for (String line:
             lines) {
            digitNo++;
            for (int i = 0; i < line.length(); i++) {
                currKey = moveOnNumericKeypad(currKey, line.charAt(i));
                System.out.printf(" - Moved %s to %d\n", line.charAt(i), currKey);
            }

            System.out.printf("Digit %d is %d\n", digitNo, currKey);
            code += currKey;
        }

        System.out.printf("Code to enter is %s\n", code);
        return code;
    }

    public static String determineBathroomCodeOnDiamondKeypad(String input) {
        char currKey = '5';
        int digitNo = 0;
        String code = "";

        String[] lines = input.split("\\r?\\n");
        for (String line:
                lines) {
            digitNo++;
            for (int i = 0; i < line.length(); i++) {
                currKey = moveOnDiamondKeypad(currKey, line.charAt(i));
                System.out.printf(" - Moved %s to %c\n", line.charAt(i), currKey);
            }

            System.out.printf("Digit %d is %c\n", digitNo, currKey);
            code += currKey;
        }

        System.out.printf("Code to enter is %s\n", code);
        return code;
    }

    static int moveOnNumericKeypad(int startPos, char direction) {
        return switch (direction) {
            case 'U' -> startPos < 4 ? startPos : startPos - 3;
            case 'R' -> startPos == 3 || startPos == 6 || startPos == 9 ? startPos : startPos + 1;
            case 'D' -> startPos > 6 ? startPos : startPos + 3;
            case 'L' -> startPos == 1 || startPos == 4 || startPos == 7 ? startPos : startPos - 1;
            default -> throw new RuntimeException("Invalid direction");
        };
    }

    static char moveOnDiamondKeypad(char startPos, char direction) {
        switch (direction) {
            case 'U' -> {
                return switch (startPos) {
                    case '1' -> '1';
                    case '2' -> '2';
                    case '3' -> '1';
                    case '4' -> '4';
                    case '5' -> '5';
                    case '6' -> '2';
                    case '7' -> '3';
                    case '8' -> '4';
                    case '9' -> '9';
                    case 'A' -> '6';
                    case 'B' -> '7';
                    case 'C' -> '8';
                    case 'D' -> 'B';
                    default -> throw new RuntimeException("Invalid start position");
                };
            }
            case 'R' -> {
                return switch (startPos) {
                    case '1' -> '1';
                    case '2' -> '3';
                    case '3' -> '4';
                    case '4' -> '4';
                    case '5' -> '6';
                    case '6' -> '7';
                    case '7' -> '8';
                    case '8' -> '9';
                    case '9' -> '9';
                    case 'A' -> 'B';
                    case 'B' -> 'C';
                    case 'C' -> 'C';
                    case 'D' -> 'D';
                    default -> throw new RuntimeException("Invalid start position");
                };
            }
            case 'D' -> {
                return switch (startPos) {
                    case '1' -> '3';
                    case '2' -> '6';
                    case '3' -> '7';
                    case '4' -> '8';
                    case '5' -> '5';
                    case '6' -> 'A';
                    case '7' -> 'B';
                    case '8' -> 'C';
                    case '9' -> '9';
                    case 'A' -> 'A';
                    case 'B' -> 'D';
                    case 'C' -> 'C';
                    case 'D' -> 'D';
                    default -> throw new RuntimeException("Invalid start position");
                };
            }
            case 'L' -> {
                return switch (startPos) {
                    case '1' -> '1';
                    case '2' -> '2';
                    case '3' -> '2';
                    case '4' -> '3';
                    case '5' -> '5';
                    case '6' -> '5';
                    case '7' -> '6';
                    case '8' -> '7';
                    case '9' -> '8';
                    case 'A' -> 'A';
                    case 'B' -> 'A';
                    case 'C' -> 'B';
                    case 'D' -> 'D';
                    default -> throw new RuntimeException("Invalid start position");
                };
            }
            default -> throw new RuntimeException("Invalid direction");
        }
    }
}
