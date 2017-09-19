package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String str = bufferedReader.readLine();
        return str;
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new ArrayList<>();

        writeMessage(Dish.allDishesToString());
        writeMessage("Введите блюдо:");

        String str = readString();
        while (!str.equalsIgnoreCase("exit")) {
            if (Dish.allDishesToString().toLowerCase().contains(str.toLowerCase())) {
                dishList.add(Dish.valueOf(str));
            } else {
                writeMessage("Такого блюда нет.");
            }
            str = readString();
        }

        return dishList;
    }
}
