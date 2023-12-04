package com.adventofcode.year2023.day1;

import com.adventofcode.DayMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day1 {
    private static final Logger logger = LogManager.getLogger(Day1.class);

    private DayMode mode;
    private int total;

    public Day1(String filename, DayMode mode) {
        this.mode = mode;
        processFile(filename);
    }

    private void processFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(this::processLine);
        } catch (IOException e) {
            logger.error("{}", e);
        }
    }

    private void processLine(String line) {
        logger.debug(line);

        String regex = "(\\d)";
        if (this.mode == DayMode.PART2) {
            regex = "([\\d]|one|two|three|four|five|six|seven|eight|nine)";
        }
        Pattern p = Pattern.compile(regex);

        int index = 0;
        int match = 0;
        char first = '0', last = '0';
        while (index < line.length()) {
            Matcher m = p.matcher(line.substring(index));
            if (m.find()) {
                match++;
                String digitStr = m.group();
                logger.debug("Match: [" + match + "] is: [" + digitStr + "]");
                if (1 == match) {
                    first = toChar(digitStr);
                }
                last = toChar(digitStr);

                index += m.start() + 1;
            } else {
                break;
            }
        }
        logger.debug("First: [" + first + "], Last: [" + last + "]");

        String numberStr = new StringBuilder()
                .append(first)
                .append(last)
                .toString();

        Integer number = Integer.parseInt(numberStr, 10);
        logger.debug("Generated value: [" + number + "]");

        total += number;
    }

    private char toChar(String digit) {
        char retVal = '0';
        if (null != digit) {
            if (digit.charAt(0) >= 48 && digit.charAt(0) <= 57) {
                retVal = digit.charAt(0);
            }
            else {
                switch (digit) {
                    case "one":
                        retVal='1';
                        break;
                    case "two":
                        retVal='2';
                        break;
                    case "three":
                        retVal='3';
                        break;
                    case "four":
                        retVal='4';
                        break;
                    case "five":
                        retVal='5';
                        break;
                    case "six":
                        retVal='6';
                        break;
                    case "seven":
                        retVal='7';
                        break;
                    case "eight":
                        retVal='8';
                        break;
                    case "nine":
                        retVal='9';
                        break;
                }
            }
        }
        return retVal;
    }

    public int getTotal() {
        return total;
    }
}