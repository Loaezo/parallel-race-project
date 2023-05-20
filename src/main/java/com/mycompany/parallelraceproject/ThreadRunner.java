package com.mycompany.parallelraceproject;

import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class ThreadRunner implements Runnable {
    private Runner runner;
    private JTextArea orderTextArea;
    private static int counter = 1;

    public ThreadRunner(Runner runner, JTextArea orderTextArea) {
        this.runner = runner;
        this.orderTextArea = orderTextArea;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(runner.getSpeed() * 100);
            synchronized (orderTextArea) {
                orderTextArea.append("¡" + runner.getName() + " ha llegado a la meta en logar número " + counter + "!\n");
                counter +=1;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
