package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<Integer> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }

        Integer last = Integer.MIN_VALUE;
        Integer max = 1;
        Integer maxNow = 1;

        for (Integer i : list) {
            maxNow = (i == last) ? maxNow + 1 : 1;
            last = i;
            if (max < maxNow) {
                max = maxNow;
            }
        }
        System.out.println(max);
    }
}