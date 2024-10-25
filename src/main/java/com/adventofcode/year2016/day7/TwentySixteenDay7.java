package com.adventofcode.year2016.day7;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TwentySixteenDay7 {

    // ABBA == Autonomous Bridge Bypass Annotation
    static Predicate<String> hasIpAbba = ip -> {
        Predicate<String> isABBA = s -> s.length() == 4
                && s.charAt(0) == s.charAt(3) && s.charAt(1) == s.charAt(2)
                && s.charAt(0) != s.charAt(1)
                && s.charAt(0) >= 'a' && s.charAt(0) <= 'z'
                && s.charAt(1) >= 'a' && s.charAt(1) <= 'z'
                && s.charAt(2) >= 'a' && s.charAt(2) <= 'z'
                && s.charAt(3) >= 'a' && s.charAt(3) <= 'z';

        int currChar = -1;
        boolean inBrackets = false;
        boolean foundABBAOutsideBrackets = false;

        while (ip.length() > currChar + 4) {
            currChar++;

            if (ip.charAt(currChar) == '[') {
                inBrackets = true;
            } else if (ip.charAt(currChar) == ']') {
                inBrackets = false;
            }

            if (isABBA.test(ip.substring(currChar, currChar+4))) {
                if (inBrackets) {
                    return false;
                } else {
                    // Can't return yet because we might find one in brackets subsequently
                    foundABBAOutsideBrackets = true;
                }
            }
        }

        return foundABBAOutsideBrackets;
    };

    static long countIPsSupportingTLS(String input) {
        return Arrays.stream(input.split(System.getProperty("line.separator"))).filter(hasIpAbba).count();
    }
}
