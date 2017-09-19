package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int day = 365;
        int year = Integer.parseInt(reader.readLine());

        if (year%100 == 0) {
            if (year%400 == 0) {
                day = 366;
            }
        } else {
            if (year%4 == 0) {
                day = 366;
            }
        }
        System.out.println("количество дней в году: " + day);
        //напишите тут ваш код
    }
}