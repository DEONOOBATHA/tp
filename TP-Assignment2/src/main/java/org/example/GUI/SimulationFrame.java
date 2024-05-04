package org.example.GUI;

import org.example.Model.Server;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationFrame extends JFrame {
    private JTextArea serverStatus;

    public SimulationFrame() {
        JLabel timeL = new JLabel("Time");
        JTextField time = new JTextField();
        JLabel waitingClientsL = new JLabel("Waiting clients:");
        JTextField waitingClients = new JTextField();

        JPanel mainPanel = new JPanel(new GridLayout(4, 1));
        mainPanel.add(timeL);
        mainPanel.add(time);
        mainPanel.add(waitingClientsL);
        mainPanel.add(waitingClients);

        serverStatus = new JTextArea();
        mainPanel.add(new JScrollPane(serverStatus));

        this.setContentPane(mainPanel);
        this.setTitle("Log of Events");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setVisible(true);
    }

//    public void displayServerStatus(List<Server> servers) {
//        StringBuilder serverStatusText = new StringBuilder();
//        for (int i = 0; i < servers.size(); i++) {
//            serverStatusText.append("Queue ").append(i + 1).append(": ");
//            if (servers.get(i).isBusy()) {
//                serverStatusText.append("Busy\n");
//            } else {
//                serverStatusText.append("Available\n");
//            }
//        }
//        serverStatus.setText(serverStatusText.toString());
//    }
}
