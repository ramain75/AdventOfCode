package com.adventofcode.year2023.day2;

import com.adventofcode.DayMode;
import com.adventofcode.year2023.day1.Day1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Day2 {
    private static final Logger logger = LogManager.getLogger(Day1.class);

    private static final String RED = "red";
    private static final String GREEN = "green";
    private static final String BLUE = "blue";

    private static final Map<String, Integer> part1CubeSizes;
    static {
        Map<String, Integer> cubeSizes = new HashMap<>();
        cubeSizes.put(RED, 12);
        cubeSizes.put(GREEN, 13);
        cubeSizes.put(BLUE, 14);
        part1CubeSizes = Collections.unmodifiableMap(cubeSizes);
    }

    private DayMode mode;
    private int possibleGameIdsTotal;
    private int powersTotal;

    public Day2(String filename, DayMode mode) {
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
        logger.debug("{}", line);

        // Split the line
        // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        String[] lineParts = line.split(":");
        String game = (lineParts[0].split(" "))[1];
        String[] rounds = lineParts[1].split(";");

        logger.debug("Game [{}] has [{}] rounds", game, rounds.length);

        Map<String, Integer> highestBallCounts = new HashMap<>();
        highestBallCounts.put(RED, 0);
        highestBallCounts.put(GREEN, 0);
        highestBallCounts.put(BLUE, 0);

        for (String round: rounds) {
            logger.debug(" Processing round [{}]", round);
            // Get a count of each ball colour in this round
            Map<String, Integer> roundBallCounts = getBallCounts(round);
            // Get the max of each ball colour in this round and that we have from the previous rounds
            highestBallCounts = getHighestBallCountsByColour(highestBallCounts, roundBallCounts);
        }

        // Work out if this game is possible given the part1CubeSizes
        boolean possibleGame = isPossibleGame(highestBallCounts);
        logger.debug("Game [{}] is {}possible", game, possibleGame ? "" : "im");
        if (possibleGame) {
            possibleGameIdsTotal += Integer.parseInt(game, 10);
        }

        powersTotal += multiplyHighestBallCounts(highestBallCounts);
    }

    private int multiplyHighestBallCounts(Map<String, Integer> highestBallCounts) {
        int red = 0, green = 0, blue = 0;
        if (null != highestBallCounts.get(RED)) {
            red = highestBallCounts.get(RED);
        }
        if (null != highestBallCounts.get(GREEN)) {
            green = highestBallCounts.get(GREEN);
        }
        if (null != highestBallCounts.get(BLUE)) {
            blue = highestBallCounts.get(BLUE);
        }
        int retval = red * green * blue;
        logger.debug("red: [{}], green: [{}], blue: [{}], total: [{}]", red, green, blue, retval);
        return retval;
    }

    private Map<String, Integer> getHighestBallCountsByColour(Map<String, Integer> highestBallCounts, Map<String, Integer> roundBallCounts) {
        Map<String, Integer> retval = highestBallCounts;

        if (null != roundBallCounts.get(RED) && roundBallCounts.get(RED) > highestBallCounts.get(RED)) {
            retval.put(RED, roundBallCounts.get(RED));
        }
        if (null != roundBallCounts.get(GREEN) && roundBallCounts.get(GREEN) > highestBallCounts.get(GREEN)) {
            retval.put(GREEN, roundBallCounts.get(GREEN));
        }
        if (null != roundBallCounts.get(BLUE) && roundBallCounts.get(BLUE) > highestBallCounts.get(BLUE)) {
            retval.put(BLUE, roundBallCounts.get(BLUE));
        }

        return retval;
    }

    private boolean isPossibleGame(Map<String, Integer> ballCounts) {
        if (null != ballCounts.get(RED) && ballCounts.get(RED) > part1CubeSizes.get(RED)) {
            return false;
        }
        if (null != ballCounts.get(GREEN) && ballCounts.get(GREEN) > part1CubeSizes.get(GREEN)) {
            return false;
        }
        if (null != ballCounts.get(BLUE) && ballCounts.get(BLUE) > part1CubeSizes.get(BLUE)) {
            return false;
        }

        return true;
    }

    private Map<String, Integer> getBallCounts(String round) {
        Map<String, Integer> ballCounts = new HashMap<>();
        String[] balls = round.split(",");
        for (String ball: balls) {
            String[] operands = ball.split(" ");
            // String starts with a space, so ignore it
            String count = operands[1];
            String colour = operands[2];
            logger.debug("  Found colour [{}], count [{}]", colour, count);

            ballCounts.put(colour, Integer.parseInt(count));
        }

        return ballCounts;
    }

    public int getPossibleGameIdsTotal() {
        return possibleGameIdsTotal;
    }

    public int getPowersTotal() {
        return powersTotal;
    }
}
