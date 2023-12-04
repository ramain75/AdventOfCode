package com.adventofcode.year2023.day3;

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

    List<String> engineSchematic = new ArrayList<>();

    int partNumberTotal;

    public Day3(String filename) {
        processFile(filename);
    }

    private void processFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(this::processLine);
        } catch (IOException e) {
            logger.error("{}", e);
        }

        for (int i = 0; i < engineSchematic.size(); i++) {
            findPartNumbersOnEngineLine(i);
        }
    }

    private void findPartNumbersOnEngineLine(int lineNumber) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(engineSchematic.get(lineNumber));

        while(m.find()) {
            String digitStr = m.group();
            int startIndex = m.start();
            int endIndex = m.end();
            boolean part = isPartNumber(lineNumber, startIndex, endIndex);
            if (part) {
                partNumberTotal += Integer.parseInt(digitStr);
            }
        }
    }

    private boolean isPartNumber(int lineNumber, int startIndex, int endIndex) {
        // TODO: Currently doesn't check the engine around the number
        return true;
    }

    private void processLine(String line) {
        logger.debug("{}", line);
        engineSchematic.add(line);
    }

    public int getPartNumberTotal() {
        return partNumberTotal;
    }
}
