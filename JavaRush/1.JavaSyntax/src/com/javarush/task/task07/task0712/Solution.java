package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(reader.readLine());
        }

        String minStr = list.get(0);
        String maxStr = list.get(0);
        for (String string: list) {
            if (string.length() < minStr.length()) {
                minStr = string;
            }
            if (string.length() > maxStr.length()) {
                maxStr = string;
            }
        }

        if (list.indexOf(maxStr) < list.indexOf(minStr)) {
            System.out.println(maxStr);
        } else {
            System.out.println(minStr);
        }


        //напишите тут ваш код
    }
}
