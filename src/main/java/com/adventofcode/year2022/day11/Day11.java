package com.adventofcode.year2022.day11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day11 {
    private static final Logger logger = LogManager.getLogger(Day11.class);
    List<Monkey> monkeys = new ArrayList<>();
    Monkey currentMonkey;

    enum Day11Mode {
        PART1,
        PART2
    }

    public Day11(String filename, int rounds, Day11Mode mode) {
        processFile(filename);
        playPiggyInTheMiddle(rounds, mode);
    }

    private void processFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename), StandardCharsets.UTF_8)) {
            stream.forEach(this::processLine);
            currentMonkey = null;
            logger.debug(monkeys);
        } catch (IOException e) {
            logger.error("{}", e);
        }
    }

    private void processLine(String line) {
        logger.debug(line);
        String[] tokens = line.split(" ");

        if (tokens.length > 0) {
            if (2 <= tokens.length && tokens[0].equalsIgnoreCase("Monkey")) {
                String monkeyNumber = Arrays.stream(tokens[1].split(":"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Unable to parse monkey number"));
                currentMonkey = new Monkey(monkeyNumber);
                monkeys.add(currentMonkey);
            }
            else if (null == currentMonkey) {
                throw new IllegalArgumentException("Invalid operation, no current monkey: " + line);
            }
            else if (3 <= tokens.length && tokens[2].equalsIgnoreCase("Starting")) {
                Arrays.stream(line.split("[:, ]"))
                        .filter(item -> item.matches("[0-9]+"))
                        .forEach(i -> currentMonkey.addItem(new Item(i)));
            }
            else if (8 == tokens.length && tokens[2].equalsIgnoreCase("Operation:")) {
                String operator = tokens[6];
                String secondOperand = tokens[7];
                if (!(tokens[3].equalsIgnoreCase("new") &&
                        tokens[4].equalsIgnoreCase("=") &&
                        tokens[5].equalsIgnoreCase("old")) ||
                        !operator.matches("[\\+\\-\\*\\/]") ||
                        !secondOperand.matches("old|\\d+")
                ) {
                    throw new IllegalArgumentException("Invalid operation, doesn't match expected pattern: " + line);
                }
                currentMonkey.setOperator(operator.charAt(0));
                currentMonkey.setOperand(secondOperand);
            }
            else if (6 == tokens.length && tokens[2].equalsIgnoreCase("Test:")) {
                if (!(tokens[3].equalsIgnoreCase("divisible") &&
                        tokens[4].equalsIgnoreCase("by")
                )) {
                    throw new IllegalArgumentException("Invalid if statement: " + line);
                }
                String s = tokens[5];
                BigInteger i = new BigInteger(s);
                currentMonkey.setDivisibleBy(i);
            }
            else if (10 == tokens.length && tokens[4].equalsIgnoreCase("If")) {
                if (!(tokens[6].equalsIgnoreCase("throw") &&
                        tokens[7].equalsIgnoreCase("to") &&
                        tokens[8].equalsIgnoreCase("monkey")
                )) {
                    throw new IllegalArgumentException("Invalid if statement: " + line);
                }
                String s = tokens[9];
                int i = Integer.parseInt(s);
                if (tokens[5].equalsIgnoreCase("true:")) {
                    currentMonkey.setTrueMonkey(i);
                } else if (tokens[5].equalsIgnoreCase("false:")) {
                    currentMonkey.setFalseMonkey(i);
                }
            }

        } else {
            currentMonkey = null;
        }
    }

    private void playPiggyInTheMiddle(int rounds, Day11Mode mode) {
        for (int round = 1; round <= rounds; round++) {
            for (Monkey monkey: monkeys) {
                if (1 == round) {
                    monkey.resetItemInspectionCount();
                }
                logger.debug("Monkey " + monkey.getNumber() + ":");

                Item item = monkey.getItems().poll();
                while (null != item) {
                    BigInteger currentWorryLevel = item.getWorryLevel();
                    monkey.incrementItemInspectionCount();
                    logger.debug("  Monkey inspects an item with a worry level of " + currentWorryLevel + ".");

                    BigInteger secondOperand;
                    String operatorText = "";
                    if (monkey.getOperand().equalsIgnoreCase("old")) {
                        secondOperand = currentWorryLevel;
                    } else {
                        secondOperand = new BigInteger(monkey.getOperand());
                    }

                    switch (monkey.getOperator()) {
                        case '+':
                            currentWorryLevel = currentWorryLevel.add(secondOperand);
                            operatorText = "increases by";
                            break;
                        case '-':
                            currentWorryLevel = currentWorryLevel.subtract(secondOperand);
                            operatorText = "decreases by";
                            break;
                        case '*':
                            currentWorryLevel = currentWorryLevel.multiply(secondOperand);
                            operatorText = "is multiplied by";
                            break;
                        case '/':
                            currentWorryLevel = currentWorryLevel.divide(secondOperand);
                            operatorText = "is divided by";
                            break;
                    }
                    logger.debug("    Worry level " + operatorText + " " + secondOperand + " to " + currentWorryLevel + ".");

                    if (Day11Mode.PART1 == mode) {
                        currentWorryLevel = currentWorryLevel.divide(new BigInteger("3"));
                        logger.debug("    Monkey gets bored with item. Worry level is divided by 3 to " + currentWorryLevel + ".");
                    }
                    item.setWorryLevel(currentWorryLevel);

                    // Perform test
                    String divisible = "";
                    int monkeyToThrowTo = monkey.getTrueMonkey();
                    BigInteger worryMod = currentWorryLevel.mod(monkey.getDivisibleBy());
                    if (!BigInteger.ZERO.equals(worryMod)) {
                        divisible = "not ";
                        monkeyToThrowTo = monkey.getFalseMonkey();
                    }
                    logger.debug("    Current worry level is " + divisible + "divisible by " + monkey.getDivisibleBy() + ".");
                    logger.debug("    Item with worry level " + currentWorryLevel + " is thrown to monkey " + monkeyToThrowTo + ".");
                    monkeys.get(monkeyToThrowTo).addItem(item);
                    item = monkey.getItems().poll();
                }
            }

            logger.debug(getWorryLevelByMonkeySummary());
        }

        logger.debug(getMonkeyInspectedItemsSummary());
        logger.debug("Monkey business is: " + calculateMonkeyBusiness());
    }

    BigInteger calculateMonkeyBusiness() {
        long highestInspected = 0L;
        long secondHighestInspected = 0L;
        for (Monkey monkey : monkeys) {
            long curr = monkey.getItemInspectionCount();
            if (curr > highestInspected) {
                secondHighestInspected = highestInspected;
                highestInspected = curr;
            } else if (curr > secondHighestInspected) {
                secondHighestInspected = curr;
            }
        }

        return BigInteger.valueOf(highestInspected).multiply(BigInteger.valueOf(secondHighestInspected));
    }

    String getMonkeyInspectedItemsSummary() {
        StringBuffer sb = new StringBuffer();
        for (Monkey monkey : monkeys) {
            sb.append(monkey.getInspectedItemsSummary());
        }

        return sb.toString();
    }

    String getWorryLevelByMonkeySummary() {
        StringBuffer sb = new StringBuffer();
        for (Monkey monkey : monkeys) {
            sb.append(monkey.getSummary());
        }

        return sb.toString();
    }
}
