package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        getEvenOdd(Integer.parseInt(reader.readLine()));
        System.out.printf("Even: %d Odd: %d", even, odd);
        //напишите тут ваш код
    }

    private static void getEvenOdd (int number) {
        int fNumber = number;

        while (fNumber > 0) {
            if ((fNumber % 10) % 2 == 0) {
                even++;
            } else {
                odd++;
            }
            fNumber /= 10;
        }
    }
}
