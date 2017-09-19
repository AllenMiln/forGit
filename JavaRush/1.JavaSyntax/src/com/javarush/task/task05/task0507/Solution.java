package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int i = 0;
        int sumNumber = 0;
        for (String str = reader.readLine(); !str.equals("-1"); str = reader.readLine()){
            sumNumber+=Integer.parseInt(str);
            i++;
        }
        System.out.println((double) sumNumber/i);
    }
}

