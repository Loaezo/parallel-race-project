package com.mycompany.parallelraceproject;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ThreadRunner implements Runnable {
    private Runner runner;
    private JTextArea orderTextArea;
    private static int counter = 1;
    private boolean isRaceFinished = false;

    public ThreadRunner(Runner runner, JTextArea orderTextArea) {
        this.runner = runner;
        this.orderTextArea = orderTextArea;
    }
        @Override
        public void run() {
            try {
                Thread.sleep(runner.getSpeed() * 100);
                synchronized (orderTextArea) {
                    if (!isRaceFinished) {
                        SwingUtilities.invokeLater(() -> {
                            orderTextArea.append("¡" + runner.getName() + " ha llegado a la meta en lugar número " + counter + "!\n");
                            counter++;
                            
                        });
                        if (counter == 5) {
                            isRaceFinished = true;
                            showFinishMessage();
                            counter = 1;
                        };
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
     }
    private void showFinishMessage() {
        JOptionPane.showMessageDialog(null, "¡Carrera finalizada!");
    }

}
