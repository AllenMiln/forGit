package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());

        if ((number >= 1) && (number <= 999)) {
            String signOne = (number % 2 == 0) ? "четное" : "нечетное";
            String signTwo = "";
            switch ((int) Math.log10(number) + 1) {
                case 1:
                    signTwo = "однозначное";
                    break;
                case 2:
                    signTwo = "двузначное";
                    break;
                case 3:
                    signTwo = "трехзначное";
                    break;
            }
            System.out.println(signOne + " " + signTwo + " число");
        }
    }
}
