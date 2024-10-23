package com.adventofcode.year2016.day4;

import org.apache.maven.surefire.shared.lang3.StringUtils;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TwentySixteenDay4 {

    public static int sumSectorIDsForRealRooms(String input) {

        int sectorIDSum = 0;
        for (String possibleRoom:
             input.split(System.getProperty("line.separator"))) {
            sectorIDSum += isRealRoom(possibleRoom);
        }

        return sectorIDSum;
    }

    public static int findSectorIDMatching(String input, String expectedRoomName) {

        int sectorID = 0;
        for (String possibleRoom:
                input.split(System.getProperty("line.separator"))) {
            sectorID = isRealRoom(possibleRoom);
            if (sectorID > 0) {
                final String roomName = decryptValidRoomUsingShiftCipher(possibleRoom);
                System.out.printf("Decrypted room name as [%s]\n", roomName);
                if (roomName.indexOf(expectedRoomName) > -1) {
                    System.out.printf("Found match for [%s] in [%s] with sector ID [%d]\n", expectedRoomName, roomName, sectorID);
                    return sectorID;
                }
            }
        }

        return 0;
    }


    /**
     * Determines whether this is a real room or not
     * @param input Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets
     * @return 0 if not a real room or the checksum if it is real
     */
    static int isRealRoom(String input) {
        String checksum;
        int sectorID;

        Pattern checksumPattern = Pattern.compile("\\[([a-zA-Z]+)\\]");
        Matcher checksumMatcher = checksumPattern.matcher(input);
        if (checksumMatcher.find() && checksumMatcher.groupCount() == 1) {
            checksum = checksumMatcher.group(1);
        } else {
            throw new RuntimeException("Invalid room, no checksum found");
        }

        Pattern sectorIDPattern = Pattern.compile("(\\d+)");
        Matcher sectorIDMatcher = sectorIDPattern.matcher(input);
        if (sectorIDMatcher.find() && sectorIDMatcher.groupCount() == 1) {
            sectorID = Integer.parseInt(sectorIDMatcher.group(1));
        } else {
            throw new RuntimeException("Invalid room, no sector ID found");
        }

        char[] chars = input.toCharArray();
        Map<Character, Integer> charMap = new TreeMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '-') {
                continue;
            }
            if (chars[i] >= '0' && chars[i] <= '9') {
                break;
            }
            charMap.put(chars[i], charMap.getOrDefault(chars[i], 0)+1);
        }

        StringBuilder sb = new StringBuilder();
        try {
            while (sb.length() < 5) {
                Integer maxCount = charMap.values().stream().max(Integer::compare).get();

                charMap.forEach((key, value) -> {
                    if (value == maxCount) {
                        // System.out.printf("Found key %c with value %d\n", key, value);
                        sb.append(key);
                    }
                });

                charMap.entrySet().removeIf(entry -> entry.getValue().equals(maxCount));
            }
        } catch (NoSuchElementException nsee) {
            // Ignore
        }

        String calculatedChecksum = sb.substring(0, Math.min(5, sb.length()));
        if (calculatedChecksum.equals(checksum)) {
            // System.out.printf("Checksum matches: %s\n", calculatedChecksum);
            return sectorID;
        }

        // System.out.printf("Calculated checksum [%s] does not match expected checksum [%s]\n", calculatedChecksum, checksum);
        return 0;
    }

    static String decryptValidRoomUsingShiftCipher(String input) {
        int sectorID;
        int roomNameLength;
        int currChar = 0;

        Pattern sectorIDPattern = Pattern.compile("(\\d+)");
        Matcher sectorIDMatcher = sectorIDPattern.matcher(input);
        if (sectorIDMatcher.find() && sectorIDMatcher.groupCount() == 1) {
            roomNameLength = input.indexOf(sectorIDMatcher.group(1)) - 1;
            sectorID = Integer.parseInt(sectorIDMatcher.group(1));
        } else {
            throw new RuntimeException("Invalid room, no sector ID found");
        }

        if (roomNameLength == 0) {
            throw new RuntimeException("Invalid room");
        }
        char[] inputArr = input.toCharArray();
        char[] outputArr = new char[roomNameLength];
        while (currChar < roomNameLength) {
            if (inputArr[currChar] == '-') {
                outputArr[currChar] = ' ';
            } else {
                // a == 0, z == 25
                int letterDigit = (int) inputArr[currChar] - 97;
                int rotateTimes = sectorID % 26;
                int realLetter = ((letterDigit + rotateTimes) % 26) + 97;
                outputArr[currChar] = (char) realLetter;
            }
            currChar++;
        }

        return StringUtils.valueOf(outputArr);
    }
}
