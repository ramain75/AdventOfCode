package com.adventofcode.year2016.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TwentySixteenDay7 {

    static Predicate<String> isABBA = s -> s.length() == 4
            && s.charAt(0) == s.charAt(3) && s.charAt(1) == s.charAt(2)
            && s.charAt(0) != s.charAt(1)
            && s.charAt(0) >= 'a' && s.charAt(0) <= 'z'
            && s.charAt(1) >= 'a' && s.charAt(1) <= 'z';

    static Predicate<String> isABAorBAB = s -> s.length() == 3
            && s.charAt(0) == s.charAt(2)
            && s.charAt(0) != s.charAt(1)
            && s.charAt(0) >= 'a' && s.charAt(0) <= 'z'
            && s.charAt(1) >= 'a' && s.charAt(1) <= 'z';

    static Function<String, String> reverse = s -> {
        if (isABAorBAB.test(s)) {
            return String.valueOf(s.charAt(1)) +
                    s.charAt(0) +
                    s.charAt(1);
        }
        throw new RuntimeException("Invalid ABA or BAB");
    };

    // ABBA == Autonomous Bridge Bypass Annotation
    static Predicate<String> hasIpAbba = ip -> {
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

    static Predicate<String> supportsSSL = ip -> {
        List<String> areaBroadcastAccessors = new ArrayList<>();
        List<String> byteAllocationBlocks = new ArrayList<>();

        System.out.printf("Processing: %s\n", ip);

        int currChar = -1;
        boolean inBrackets = false;

        while (ip.length() > currChar + 3) {
            currChar++;

            if (ip.charAt(currChar) == '[') {
                inBrackets = true;
            } else if (ip.charAt(currChar) == ']') {
                inBrackets = false;
            }

            String possibleABAorBAB = ip.substring(currChar, currChar+3);
            if (isABAorBAB.test(possibleABAorBAB)) {
                if (inBrackets) {
                    // We're cheating here by reversing before putting in , so these are technically ABAs
                    byteAllocationBlocks.add(reverse.apply(possibleABAorBAB));
                } else {
                    areaBroadcastAccessors.add(possibleABAorBAB);
                }
            }
        }

        System.out.printf("Area Broadcast Accessors: %s\n", areaBroadcastAccessors.toString());
        System.out.printf("Byte Allocation Blocks: %s\n", byteAllocationBlocks.toString());

        areaBroadcastAccessors.retainAll(byteAllocationBlocks);

        System.out.printf("Intersecting ABAs and BABs: %s\n", areaBroadcastAccessors.toString());

        return !areaBroadcastAccessors.isEmpty();
    };

    static long countIPsSupportingSSL(String input) {
        return Arrays.stream(input.split(System.getProperty("line.separator"))).filter(supportsSSL).count();
    }
}
