package com.javarush.task.task29.task2909.human;

/**
 * Created by admin on 20.03.2017.
 */
public class Soldier extends Human{

    public Soldier(String name, int age) {
        super(name, age);
    }

    public void live() {
        fight();
    }

    public void fight() {
    }

}
