package com.mycompany.parallelraceproject;

import java.util.Random;

public class Runner {
    private String name;
    private float speed;
    private float randomNum;

    public Runner(String name) {
        this.name = name;
        this.speed = generateRandomSpeed();
    }

    public String getName() {
        return name;
    }

    public float getSpeed() {
        return speed;
    }

    private float generateRandomSpeed() {
        Random random = new Random();
        randomNum = random.nextInt(21);
        return (50/randomNum);
}
}

