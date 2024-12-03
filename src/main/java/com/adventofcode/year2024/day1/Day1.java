package com.adventofcode.year2024.day1;

import java.util.*;
import java.util.stream.Collectors;

public class Day1 {

    List<Integer> group1 = new ArrayList<>();
    List<Integer> group2 = new ArrayList<>();
    Map<Integer, Integer> group2Counts = new HashMap<>();

    Day1(String input) {
        readLists(input);
    }

    void readLists(String input) {
        Arrays.stream(input.split("\\r?\\n")).forEach(s -> {
            String[] items = s.split("\\s+");
            int a = Integer.parseInt(items[0]);
            int b = Integer.parseInt(items[1]);
            group1.add(a);
            group2.add(b);

            group2Counts.put(b, group2Counts.getOrDefault(b, 0) + 1);
        });

        System.out.println("group1: " + group1);
        System.out.println("group2: " + group2);
        System.out.println("group2Counts: " + group2Counts);
    }

    public int getTotalDistanceBetweenGroups() {
        int total = 0;

        List<Integer> g1, g2;
        g1 = group1.stream().sorted().toList();
        g2 = group2.stream().sorted().toList();

        for (int i = 0; i < g1.size(); i++) {
            total += Math.abs(g1.get(i) - g2.get(i));
        }

        return total;
    }

    public int getTotalSimilarityScore() {
        return group1.stream().mapToInt(i -> i * group2Counts.getOrDefault(i, 0)).sum();
    }
}
