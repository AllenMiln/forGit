package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer;
        int a = Integer.parseInt(reader.readLine());

        if (a != 0) {
            String firstSign = (a > 0) ? "положительное" : "отрицательное";
            String secondSign = (a % 2 == 0) ? "четное" : "нечетное";
            answer = firstSign + " " + secondSign + " число";
        } else {
            answer = "ноль";
        }
        System.out.println(answer);
        //напишите тут ваш код
    }
}
