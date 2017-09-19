package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);

    }

    public void createExpression(int number) {
        String str = getArithmeticSigns(number);
        StringBuilder strOut = new StringBuilder(number + " =");
        for (int i = 0, length = str.length(); i < length; i++) {
            if (str.charAt(i) != '0') {
                strOut.append(" ").append(str.charAt(i)).append(" ").append((int) Math.pow(3, i));
            }
        }
        System.out.println(strOut);
    }

    private String getArithmeticSigns (int number) {
        int a = number%3;
        int b = number/3;

        String str = "";
        switch (a) {
            case 0:
                str += "0";
                break;
            case 1:
                str += "+";
                break;
            case 2:
                str += "-";
                b += 1;
                break;
        }

        if (number != 1) {
            str += getArithmeticSigns(b);
        }

        return str;
    }
}