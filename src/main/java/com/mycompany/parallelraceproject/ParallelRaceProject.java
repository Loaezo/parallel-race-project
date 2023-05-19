package com.mycompany.parallelraceproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ParallelRaceProject() {
        setTitle("Carrera Atlética");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        registrationLabel = new JLabel("Registrar corredor");
        
        JPanel registrationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registrationTextField = new JTextField();
        registrationTextField.setPreferredSize(new Dimension(150, registrationTextField.getPreferredSize().height));
        registrationTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerButton = new JButton("Registrar");
        registerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        registrationPanel.add(registrationTextField);
        registrationPanel.add(registerButton);
        
        registeredParticipantsLabel = new JLabel("Corredores registrados");
        participantsTextArea = new JTextArea();
        participantsTextArea.setEditable(false);
        participantsTextArea.setRows(5);
        JScrollPane participantsScrollPane = new JScrollPane(participantsTextArea);

        resultsLabel = new JLabel("Resultados");
        
        JPanel resultsPanel = new JPanel(new GridLayout(1, 2));

        orderTextArea = new JTextArea();
        orderTextArea.setEditable(false);
        JScrollPane orderScrollPane = new JScrollPane(orderTextArea);
        
        JPanel resultsButtonsPanel = new JPanel();
        resultsButtonsPanel.setLayout(new BoxLayout(resultsButtonsPanel, BoxLayout.Y_AXIS));        
        
        startButton = new JButton("Iniciar Carrera");
        restartButton = new JButton("Reiniciar Carrera");
        exitButton = new JButton("Salir");
        
        resultsButtonsPanel.add(startButton);
        resultsButtonsPanel.add(restartButton);
        resultsButtonsPanel.add(exitButton);

        resultsPanel.add(orderScrollPane);
        resultsPanel.add(resultsButtonsPanel);


        add(registrationLabel);
        add(registrationPanel);
        add(registeredParticipantsLabel);
        add(participantsTextArea);
        add(resultsLabel);        
        add(resultsPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ParallelRaceProject raceInterface = new ParallelRaceProject();
            raceInterface.setVisible(true);
        });
    }
}
