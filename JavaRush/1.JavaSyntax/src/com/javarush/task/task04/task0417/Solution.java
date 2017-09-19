package com.javarush.task.task04.task0417;

/* 
Существует ли пара?
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int one = Integer.parseInt(reader.readLine());
        int two = Integer.parseInt(reader.readLine());
        int three = Integer.parseInt(reader.readLine());

        if ((one == two) && (two == three)) {
            System.out.println(one + " " + two + " " + three);
        } else {
            if (one == two) {
                System.out.println(one + " " + two);
            }
            if (two == three) {
                System.out.println(two + " " + three);
            }
            if (three == one) {
                System.out.println(one + " " + three);
            }
        }
        //напишите тут ваш код
    }
}