package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 13.12.2016.
 */
public class ConsoleHelper
{
    static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException
    {
        List<Dish> list = new ArrayList<Dish>();

        writeMessage("Please, enter the dishes!");
        writeMessage(Dish.allDishesToString());
        for (String s = readString();!s.equalsIgnoreCase("exit") && (Dish.values().length > 0);s = readString()) {
            try
            {
                list.add(Dish.valueOf(s));
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage(String.format("%s is not detected", s));
            }
        }

        return list;
    }

}
