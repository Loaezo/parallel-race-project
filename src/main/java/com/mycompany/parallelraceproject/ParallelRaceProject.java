package com.mycompany.parallelraceproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class ParallelRaceProject extends JFrame {
    private JLabel registrationLabel;
    private JTextField registrationTextField;
    private JButton registerButton;
    private JLabel registeredParticipantsLabel;
    private JTextArea participantsTextArea;
    private JLabel resultsLabel;
    private JTextArea orderTextArea;
    private JButton startButton;
    private JButton restartButton;
    private JButton exitButton;
    private Runner[] registeredRunners;
    private int numRegisteredRunners;
    
    public ParallelRaceProject() {
        setTitle("Carrera Atlética");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        registrationLabel = new JLabel("Registrar corredor");
        registrationLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        JPanel registrationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registrationTextField = new JTextField();
        registrationTextField.setPreferredSize(new Dimension(350, registrationTextField.getPreferredSize().height));
        registrationTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerButton = new JButton("Registrar");
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numRegisteredRunners < 5) {
                    String name = registrationTextField.getText().trim();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingresa el nombre del corredor.");
                    } else if (isNameRepeated(name)) {
                        JOptionPane.showMessageDialog(null, "Este corredor ya está registrado.");
                    } else {
                        Runner runner = new Runner(name);
                        registeredRunners[numRegisteredRunners] = runner;
                        numRegisteredRunners++;
                        participantsTextArea.append(runner.getName() + " - Velocidad: " + runner.getSpeed() + "\n");
                        registrationTextField.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pueden registrar más corredores. La pista sólo tiene 5 carriles.");
                }
            }
        });
        
        registrationPanel.add(registrationTextField);
        registrationPanel.add(registerButton);
        
        registeredParticipantsLabel = new JLabel("Corredores registrados");
        registeredParticipantsLabel.setHorizontalAlignment(SwingConstants.LEFT);
                
        participantsTextArea = new JTextArea();
        participantsTextArea.setEditable(false);
        participantsTextArea.setRows(5);
        JScrollPane participantsScrollPane = new JScrollPane(participantsTextArea);
        
        resultsLabel = new JLabel("Resultados");
        resultsLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel resultsPanel = new JPanel(new GridLayout(1, 2));

        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        JScrollPane orderScrollPane = new JScrollPane(orderTextArea);
        
        JPanel resultsButtonsPanel = new JPanel();
        resultsButtonsPanel.setLayout(new BoxLayout(resultsButtonsPanel, BoxLayout.Y_AXIS));        
        
        startButton = new JButton("Iniciar Carrera");
        restartButton = new JButton("Reiniciar Carrera");
        exitButton = new JButton("Salir");
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (numRegisteredRunners == 5) {
                    JOptionPane.showMessageDialog(null, "¡Y arrancan!");
                    registerButton.setEnabled(false);
                    restartButton.setEnabled(false);
                    startButton.setEnabled(false);
                    Thread[] threads = new Thread[numRegisteredRunners];
                    for (int i = 0; i < numRegisteredRunners; i++) {
                        threads[i] = new Thread(new ThreadRunner(registeredRunners[i], orderTextArea));
                    }
                    for (Thread thread : threads) {
                        thread.start();
                    }
                    for (Thread thread : threads) {
                        try {
                            thread.join();
                        } catch (InterruptedException ie) {
                            ie.printStackTrace();
                        }
                        }
                    registerButton.setEnabled(true);
                    restartButton.setEnabled(true);
                    startButton.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "¡Carrera finalizada!");

                } else {
                    JOptionPane.showMessageDialog(null, "¡No vamos a desperdiciar carriles! Registra a 5 corredores.");
                }
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRace();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Gracias por acompañarnos ¡Hasta la próxima carrera!");
                System.exit(0);
            }
        });

        
        resultsButtonsPanel.add(startButton);
        resultsButtonsPanel.add(restartButton);
        resultsButtonsPanel.add(exitButton);

        resultsPanel.add(orderScrollPane);
        resultsPanel.add(resultsButtonsPanel);


        add(registrationLabel);
        add(registrationPanel);
        add(registeredParticipantsLabel);
        add(participantsScrollPane);
        add(resultsLabel);        
        add(resultsPanel);
        
        registeredRunners = new Runner[5];
        numRegisteredRunners = 0;


    }

    private boolean isNameRepeated(String name) {
        for (int i = 0; i < numRegisteredRunners; i++) {
            if (registeredRunners[i] != null && registeredRunners[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    private void resetRace() {
        JOptionPane.showMessageDialog(null, "¡Limpiamos la pista para una nueva carrera!");
        registrationTextField.setText("");
        participantsTextArea.setText("");
        orderTextArea.setText("");
        numRegisteredRunners = 0;
        registeredRunners = new Runner[5];
        registerButton.setEnabled(true);
        startButton.setEnabled(true);
        restartButton.setEnabled(false);
    }

}
