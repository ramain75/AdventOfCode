package com.adventofcode.year2024.day3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    long sumMultiplyInstructionsInCorruptMemory(String input) {

        Pattern p = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");  //".*mul\\(\\d[1,3],\\d[1,3]\\).*");
        Matcher m = p.matcher(input);
        int matches = 0;
        long sum = 0L;

        while (m.find()) {
            int a = Integer.parseInt(m.group(1));
            int b = Integer.parseInt(m.group(2));

            sum += (long)a * b;
        }

        return sum;
    }

    long sumMultiplyInstructionsInCorruptMemoryWithDoDont(String input) {

        Pattern p = Pattern.compile("(do\\(\\))|(don't\\(\\))|(mul\\((\\d{1,3}),(\\d{1,3})\\))");
        Matcher m = p.matcher(input);
        long sum = 0L;
        boolean processInstructions = true;

        while (m.find()) {
            String grp = m.group(0);
            if (grp != null && !grp.isEmpty()) {
//                for (int i = 0; i <= m.groupCount(); i++) {
//                    System.out.println("Group: " + i + ", " + m.group(i));
//                }

                if (m.group(1) != null && m.group(1).equals("do()")) {
//                    System.out.println("DO found!");
                    processInstructions = true;
                }
                else if (m.group(2) != null && m.group(2).equals("don't()")) {
//                    System.out.println("DON'T found!");
                    processInstructions = false;
                }
                else if (processInstructions && m.group(3) != null && !m.group(3).isEmpty()) {
                    int a = Integer.parseInt(m.group(4));
                    int b = Integer.parseInt(m.group(5));
                    sum += (long) a * b;
                }
            }
        }

        return sum;
    }

}
