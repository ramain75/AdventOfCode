package com.adventofcode.year2023.day3;

import com.adventofcode.DayMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 {
    private static final Logger logger = LogManager.getLogger(Day3.class);

    private DayMode mode;

    private List<String> engineSchematic = new ArrayList<>();

    private int partNumberTotal;

    private int gearTotal;

    public Day3(String filename, DayMode mode) {
        this.mode = mode;
        processFile(filename);
    }

    private void processFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(this::processLine);
        } catch (IOException e) {
            logger.error("{}", e);
        }

        for (int i = 0; i < engineSchematic.size(); i++) {
            findPartNumbersOnEngineLine(
                    i>0 ? engineSchematic.get(i-1) : null,
                    engineSchematic.get(i),
                    i+1<engineSchematic.size() ? engineSchematic.get(i+1) : null,
                    i);
        }

        for (int i = 0; i < engineSchematic.size(); i++) {
            findGearsOnEngineLine(
                    i>0 ? engineSchematic.get(i-1) : null,
                    engineSchematic.get(i),
                    i+1<engineSchematic.size() ? engineSchematic.get(i+1) : null,
                    i);
        }
    }

    private void findPartNumbersOnEngineLine(String prevEngineLine,
                                             String currEngineLine,
                                             String nextEngineLine,
                                             int lineNumber) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(currEngineLine);

        while(m.find()) {
            String digitStr = m.group();
            int startIndex = m.start();
            int endIndex = m.end() - 1;
            boolean part = isPartNumber(prevEngineLine, currEngineLine, nextEngineLine, digitStr, lineNumber, startIndex, endIndex);
            if (part) {
                partNumberTotal += Integer.parseInt(digitStr);
            }
        }
    }

    private void findGearsOnEngineLine(String prevEngineLine,
                                             String currEngineLine,
                                             String nextEngineLine,
                                             int lineNumber) {
        for (int i = 0; i < currEngineLine.length(); i++) {
            if (currEngineLine.charAt(i) == '*') {
                logger.debug("Found possible gear at index [{}]", i);
            }
        }
    }

    /**
     * check the engine around the number for a symbol
     * return: true on first match, false if no matches
      */
    private boolean isPartNumber(String prevEngineLine,
                                 String currEngineLine,
                                 String nextEngineLine,
                                 String digitStr,
                                 int lineNumber,
                                 int startIndex,
                                 int endIndex) {
        boolean checkPrevLine = null != prevEngineLine;
        boolean checkNextLine = null != nextEngineLine;

        int startX = startIndex > 0 ? startIndex -1 : 0;
        int endX = endIndex + 1 < currEngineLine.length() ? endIndex + 1 : endIndex;

        if (checkPrevLine) {
            for (int i = startX; i <= endX; i++) {
                char c = prevEngineLine.charAt(i);
                // Look for symbols (I'm defining as not a dot or a number)
                if (c != '.' && (c < 48 || c > 57)) {
                    logger.debug("Found match for {} on previous line, char {}", digitStr, i);
                    return true;
                }
            }
        }

        if (startX < startIndex) {
            char c = currEngineLine.charAt(startX);
            if (c != '.' && (c < 48 || c > 57)) {
                logger.debug("Found match for {} on left, char {}", digitStr, startX);
                return true;
            }
        }

        if (endX > endIndex) {
            char c = currEngineLine.charAt(endX);
            if (c != '.' && (c < 48 || c > 57)) {
                logger.debug("Found match for {} on right, char {}", digitStr, endX);
                return true;
            }
        }

        if (checkNextLine) {
            for (int i = startX; i <= endX; i++) {
                char c = nextEngineLine.charAt(i);
                if (c != '.' && (c < 48 || c > 57)) {
                    logger.debug("Found match for {} on next line, char {}", digitStr, i);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Is there a digit in more than one
     * @param prevEngineLine
     * @param currEngineLine
     * @param nextEngineLine
     * @param digitStr
     * @param lineNumber
     * @param startIndex
     * @param endIndex
     * @return If this is a gear, it will return a positive number. If not, it will return 0.
     */
    private int isGear(String prevEngineLine,
                       String currEngineLine,
                       String nextEngineLine,
                       String digitStr,
                       int lineNumber,
                       int startIndex,
                       int endIndex) {
        // TODO: implement
        return 0;
    }

    private void processLine(String line) {
        logger.debug("{}", line);
        engineSchematic.add(line);
    }

    public int getPartNumberTotal() {
        return partNumberTotal;
    }

    public int getGearTotal() {
        return gearTotal;
    }
}
