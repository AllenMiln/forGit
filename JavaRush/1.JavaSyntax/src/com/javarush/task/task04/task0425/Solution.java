package com.javarush.task.task04.task0425;

/* 
Цель установлена!
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int y = Integer.parseInt(reader.readLine());
        int x = Integer.parseInt(reader.readLine());

        if ((y > 0)&&(x > 0)){
            System.out.println("1");
        }
        if ((y < 0)&&(x > 0)){
            System.out.println("2");
        }
        if ((y < 0)&&(x < 0)){
            System.out.println("3");
        }
        if ((y > 0)&&(x < 0)){
            System.out.println("4");
        }
        //напишите тут ваш код
    }
}
