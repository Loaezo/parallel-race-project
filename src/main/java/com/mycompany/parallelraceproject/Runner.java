package com.mycompany.parallelraceproject;

import java.util.Random;

public class Runner {
    private String name;
    private int speed;

    public Runner(String name) {
        this.name = name;
        this.speed = generateRandomSpeed();
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    private int generateRandomSpeed() {
        Random random = new Random();
        return random.nextInt(31);
    }
}


