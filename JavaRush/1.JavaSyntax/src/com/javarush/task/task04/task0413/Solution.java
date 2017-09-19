package com.javarush.task.task04.task0413;

/* 
День недели
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код

        String result;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch (Integer.parseInt(reader.readLine())) {
            case 1:
                result = "понедельник";
                break;
            case 2:
                result = "вторник";
                break;
            case 3:
                result = "среда";
                break;
            case 4:
                result = "четверг";
                break;
            case 5:
                result = "пятница";
                break;
            case 6:
                result = "суббота";
                break;
            case 7:
                result = "воскресенье";
                break;
            default:
                result = "такого дня недели не существует";
                break;
        }
        System.out.println(result);
    }
}