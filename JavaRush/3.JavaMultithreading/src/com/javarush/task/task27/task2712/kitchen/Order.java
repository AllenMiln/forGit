package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int totalCookingTime = 0;

        for (Dish dish: dishes) {
            totalCookingTime += dish.getDuration();
        }

        return totalCookingTime;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }
    @Override
    public String toString() {
        return dishes.size() != 0 ? String.format("Your order: %s of %s, cooking time %dmin", dishes.toString(), tablet.toString(), getTotalCookingTime()) : "";
    }
}
