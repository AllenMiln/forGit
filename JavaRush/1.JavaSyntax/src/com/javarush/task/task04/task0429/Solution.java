package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        int posNumber = 0;
        int negNumber = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            int number = Integer.parseInt(reader.readLine());
            if (number < 0) {
                negNumber++;
            }
            if ( number > 0) {
                posNumber++;
            }
        }
        System.out.println("количество отрицательных чисел: " + negNumber);
        System.out.println("количество положительных чисел: " + posNumber);
        //напишите тут ваш код
    }
}
