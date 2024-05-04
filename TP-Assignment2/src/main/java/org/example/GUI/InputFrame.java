package org.example.GUI;

import org.example.BusinessLogic.SelectionPolicy;
import org.example.BusinessLogic.SimulationManager;
import org.example.Model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InputFrame extends JFrame implements ActionListener {

    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JComboBox<String> choice;
    private JButton button;


    public InputFrame() {
        JLabel cerinta1 = new JLabel("Number of clients:");
        text1 = new JTextField();
        JLabel cerinta2 = new JLabel("Number of queues:");
        text2 = new JTextField();
        JLabel cerinta3 = new JLabel("Simulation interval:");
        text3 = new JTextField();
        JLabel cerinta4 = new JLabel("Minimum and maximum arrival time:");
        text4 = new JTextField();
        JLabel cerinta5 = new JLabel("Minimum and maximum service time:");
        text5 = new JTextField();
        JLabel cerinta6 = new JLabel("Strategy type:");
        String[] strategy = {"SHORTEST_QUEUE", "SHORTEST_TIME"};
        choice = new JComboBox<>(strategy);
        button = new JButton("SAVE");

        JPanel inputPanel = new JPanel(new GridLayout(4, 5));
        inputPanel.add(cerinta1);
        inputPanel.add(text1);
        inputPanel.add(cerinta2);
        inputPanel.add(text2);
        inputPanel.add(cerinta3);
        inputPanel.add(text3);
        inputPanel.add(cerinta4);
        inputPanel.add(text4);
        inputPanel.add(cerinta5);
        inputPanel.add(text5);
        inputPanel.add(cerinta6);
        inputPanel.add(choice);
        inputPanel.add(button);
        button.addActionListener(this);

        this.setContentPane(inputPanel);
        this.setTitle("Queue Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 250));
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button) {
            try {
                int NrClients = Integer.parseInt(text1.getText());
                int NrQueues = Integer.parseInt(text2.getText());
                int SimInterval = Integer.parseInt(text3.getText());
                String[] arrivalBounds = text4.getText().split(" ");
                int MinArrival = Integer.parseInt(arrivalBounds[0]);
                int MaxArrival = Integer.parseInt(arrivalBounds[1]);
                String[] serviceBounds = text5.getText().split(" ");
                int MinService = Integer.parseInt(serviceBounds[0]);
                int MaxService = Integer.parseInt(serviceBounds[1]);
                SelectionPolicy policy = SelectionPolicy.valueOf((String) choice.getSelectedItem());

               // SimulationManager gen = new SimulationManager(NrClients, NrQueues, SimInterval, MinArrival, MaxArrival, MinService, MaxService, policy);
                //Thread t = new Thread(gen);
                //t.start();

                // Optional: Open SimulationFrame to display simulation progress
                // SimulationFrame frame = new SimulationFrame();
                // frame.setVisible(true);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Introduceti numere naturale...va rog! :(", "Eroare", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException exception) {
                JOptionPane.showMessageDialog(null, "Eroare: " + exception.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
