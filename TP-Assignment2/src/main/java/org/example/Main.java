package org.example;

import org.example.BusinessLogic.SelectionPolicy;
import org.example.BusinessLogic.ShortestQueue;
import org.example.BusinessLogic.SimulationManager;
import org.example.BusinessLogic.Strategy;
import org.example.GUI.InputFrame;
import org.example.GUI.SimulationFrame;
import org.example.Model.Client;
import org.example.Model.Server;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        InputFrame frameInp =new InputFrame();
        Client client1= new Client(1,3,5);
        Client client2= new Client(2,4,5);
        Client client3= new Client(3,5,2);
        Client client4= new Client(4,1,3);
        Client client5= new Client(5,4,1);
        Server coada=new Server(4);
        Server coada1=new Server(4);
        coada.addTask(client1);
        coada1.addTask(client2);
        coada1.addTask(client3);
        coada1.addTask(client4);
        List<Server> servers=new ArrayList<>(2);
        servers.add(coada);
        servers.add(coada1);
        Strategy strategy=new ShortestQueue();
        strategy.addClient(servers,client5);
            SimulationManager simulationManager = new SimulationManager(100,3,100,1,80,2,10, SelectionPolicy.SHORTEST_TIME);
            Thread t = new Thread(simulationManager);
            t.start();



    }
}