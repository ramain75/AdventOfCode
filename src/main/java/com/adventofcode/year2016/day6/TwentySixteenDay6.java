package com.adventofcode.year2016.day6;

import java.util.*;

public class TwentySixteenDay6 {

    enum ComparisonType { MIN, MAX; }

    static String getErrorCorrectedMessage(String message, ComparisonType comparisonType) {
        List<Map<Character, Integer>> posList = new ArrayList<>();
        boolean initialised = false;
        int lineLength = -1;

        for (String repetition:
             message.split(System.getProperty("line.separator"))) {
            if (lineLength > 0 && lineLength != repetition.length()) {
                throw new RuntimeException("Each message is expected to be the same length. At least one line in the input is different to the others.");
            }
            lineLength = repetition.length();

            for (int i = 0; i < lineLength; i++) {
                if (!initialised) {
                    posList.add(new HashMap<>());
                }
                final Integer instances = posList.get(i).getOrDefault(repetition.charAt(i), 0) + 1;
                posList.get(i).put(repetition.charAt(i), instances);
            }
            initialised = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lineLength; i++) {
            final Optional<Map.Entry<Character, Integer>> optional = switch (comparisonType) {
                case MAX -> posList.get(i).entrySet().stream().max(
                        Map.Entry.comparingByValue()
                );
                case MIN -> posList.get(i).entrySet().stream().min(
                        Map.Entry.comparingByValue()
                );
            };

            if (optional.isEmpty()) {
                String errorMsg = String.format("Unable to find a %s value for position %d", comparisonType, i);
                throw new RuntimeException(errorMsg);
            }
            sb.append(optional.get().getKey());
        }

        return sb.toString();
    }
}
