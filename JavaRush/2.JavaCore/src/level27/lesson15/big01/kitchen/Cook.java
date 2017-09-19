package com.javarush.test.level27.lesson15.big01.kitchen;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by admin on 13.12.2016.
 */
public class Cook extends Observable implements Observer
{
    private String name;
    public Cook(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public void update(Observable o, Object order)
    {
        Order orderTrue = (Order) order;
        ConsoleHelper.writeMessage("Start cooking - " + orderTrue + ", cooking time " + orderTrue.getTotalCookingTime() + "min");

        setChanged();
        notifyObservers(order);
    }
}
