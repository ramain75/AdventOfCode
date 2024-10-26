package com.adventofcode.year2016.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * I probably should have just used String manipulation for this but I was playing around with records and lambdas.
 */
public class TwentySixteenDay8 {

    record Point(int x, int y, boolean pixelState) {
    }

    private List<List<Point>> grid = new ArrayList<>();

    private int displayWidth;

    private int displayHeight;

    public TwentySixteenDay8(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        for (int y = 0; y < displayHeight; y++) {
            List<Point> rowPoints = new ArrayList<>();
            for (int x = 0; x < displayWidth; x++) {
                rowPoints.add(new Point(x, y, false));
            }
            grid.add(rowPoints);
        }
    }

    public void rect(int width, int height) {
        for (int y = 0; y < height; y++) {
            List<Point> rowPoints = grid.get(y);
            for (int x = 0; x < width; x++) {
                rowPoints.set(x, new Point(x, y, true));
            }
        }
    }

    public void rotateColumnX(int column, int count) {
        // Get the current state of the display
        List<Point> columnPoints = new ArrayList<>();
        for (int y = 0; y < displayHeight; y++) {
            columnPoints.add(grid.get(y).get(column));
        }

        for (int y = 0; y < displayHeight; y++) {
            int newRow = (y + count) % displayHeight;
            boolean state = columnPoints.get(y).pixelState;
            grid.get(newRow).set(column, new Point(column, newRow, state));
        }
    }

    public void rotateRowY(int row, int count) {
        // Copy the elements, not a reference to a list
        List<Point> rowPoints = new ArrayList<>(grid.get(row));

        for (int x = 0; x < displayWidth; x++) {
            int newCol =  (x + count) % displayWidth;
            boolean state = rowPoints.get(x).pixelState;
            grid.get(row).set(newCol, new Point(newCol, row, state));
        }
    }

    public int countLitPixels() {
        Predicate<Point> isPixelLit = p -> p.pixelState;

        // See https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#reduce-U-java.util.function.BiFunction-java.util.function.BinaryOperator-
        return grid.stream().reduce(0, (currentCount, row) -> currentCount + (int) row.stream().filter(isPixelLit).count(), Integer::sum);
    }

    public void performInstructions(String puzzleInput) {
        int lineNo = 0;
        for (String instruction:
             puzzleInput.split(System.getProperty("line.separator"))) {
            lineNo++;
            String firstSplitRegex="=";
            String secondSplitRegex=" by ";
            if (instruction.startsWith("rect")) {
                firstSplitRegex = " ";
                secondSplitRegex = "x";
            }
            String[] operands = instruction.split(firstSplitRegex);
            if (operands.length != 2) {
                throw new RuntimeException("Invalid input for instruction on line " + lineNo);
            }
            int[] values = Arrays.stream(operands[1].split(secondSplitRegex)).mapToInt(Integer::parseInt).toArray();

            if (instruction.startsWith("rect")) {
                rect(values[0], values[1]);
            } else if (instruction.startsWith("rotate row y=")) {
                rotateRowY(values[0], values[1]);
            } else if (instruction.startsWith("rotate column x=")) {
                rotateColumnX(values[0], values[1]);
            } else {
                throw new RuntimeException("Invalid instruction");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < displayHeight; y++) {
            List<Point> rowPoints = grid.get(y);
            for (int x = 0; x < displayWidth; x++) {
                sb.append(rowPoints.get(x).pixelState ? '#' : '.');
            }
            if (y < displayHeight - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
