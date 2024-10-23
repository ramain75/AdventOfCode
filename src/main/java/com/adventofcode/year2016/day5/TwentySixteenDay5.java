package com.adventofcode.year2016.day5;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TwentySixteenDay5 {

    record MD5HashResult(String doorId, int counter, String result) {
    }

    static MessageDigest md;

    static {
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String findMD5BasedPasswordForDoorId(String doorId) {
        int index = -1;
        char[] passwordArr = new char[8];
        for (int i = 0; i < 8; i++) {
            MD5HashResult result = findFirstMD5Starting00000(doorId, index);
            passwordArr[i] = result.result().charAt(5);
            index = result.counter() + 1;
        }

        return String.valueOf(passwordArr);
    }

    public static String findMD5BasedMoreInspiredPasswordForDoorId(String doorId) {
        int index = -1;
        char[] passwordArr = "________".toCharArray();

        while (!allSet(passwordArr)) {
            MD5HashResult result = findFirstMD5Starting00000(doorId, index);
            int arrayPos = ((int)result.result().charAt(5)) - 48;
            if (arrayPos >= 0 && arrayPos <= 7 && passwordArr[arrayPos] == '_') {
                passwordArr[arrayPos] = result.result().charAt(6);
            }
            index = result.counter() + 1;
        }

        return String.valueOf(passwordArr);
    }

    static boolean allSet(char[] charArr) {
        String str = String.valueOf(charArr);
        System.out.printf("[%s]\n", str);
        if (str.indexOf('_') > -1) {
            return false;
        }
        return true;
    }

    static MD5HashResult findFirstMD5Starting00000(String doorId, int index) {
        int counter = index;
        String result;

        while (true) {
            String request = "" + doorId + counter;
            md.update(StandardCharsets.UTF_8.encode(request));
            result = String.format("%032x", new BigInteger(1, md.digest()));

            if (counter > 0 && counter % 10000 == 0) {
                System.out.print(".");
                if (counter % 800000 == 0) {
                    System.out.println();
                }
            }

            if (result.startsWith("00000")) {
                System.out.println("#");
                break;
            }

            counter++;
            if (counter == Integer.MAX_VALUE) {
                throw new RuntimeException("MD5 starting 00000 not found");
            }
        }

        return new MD5HashResult(doorId, counter, result);
    }
}
