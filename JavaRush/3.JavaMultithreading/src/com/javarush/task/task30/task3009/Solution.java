package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> setRadix = new HashSet<>();
        try {
            Integer integer = Integer.parseInt(number);
            for (int i = 2; i <= 36; i++) {
                if (isPalindrome(integer, i)) {
                    setRadix.add(i);
                }
            }
        } catch (NumberFormatException ignored) {}
        return setRadix;
    }

    private static boolean isPalindrome (Integer number, Integer radix) {

        boolean bool = true;
        String string = Integer.toString(number, radix);

        int len = string.length();
        for (int i = 0; i < len / 2; i++) {
            if (string.charAt(i) != string.charAt(len - i - 1)) {
                bool = false;
                break;
            }
        }

        return bool;
    }
}

