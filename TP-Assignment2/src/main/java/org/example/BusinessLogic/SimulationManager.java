package org.example.BusinessLogic;

import org.example.GUI.SimulationFrame;
import org.example.Model.Client;
import org.example.Model.Server;

import java.util.*;

import static java.lang.System.exit;

public class SimulationManager implements Runnable{
    public int timeLimit;//simulation time
    public int maxProccesingTime;
    public int minProccesingTime;
    public int maxArrivalime;
    public int minArrivalTime;
    public int numberOfServers;//cozi
    public int numberOfClients;//clienti
    public SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Client> generatedClients;

    public void setFrame(SimulationFrame frame) {
        this.frame = frame;
    }

    public SimulationFrame getFrame() {
        return frame;
    }

    public int getMaxArrivalime() {
        return maxArrivalime;
    }

    public void setMaxArrivalime(int maxArrivalime) {
        this.maxArrivalime = maxArrivalime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public void setMaxProccesingTime(int maxProccesingTime) {
        this.maxProccesingTime = maxProccesingTime;
    }

    public void setMinProccesingTime(int minProccesingTime) {
        this.minProccesingTime = minProccesingTime;
    }

    public void setNumberOfServers(int numberOfServers) {
        this.numberOfServers = numberOfServers;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMaxProccesingTime() {
        return maxProccesingTime;
    }

    public int getMinProccesingTime() {
        return minProccesingTime;
    }

    public int getNumberOfServers() {
        return numberOfServers;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public SimulationManager(int nrClients, int nrQueues, int simInterval, int minArrival, int maxArrival, int minService, int maxService, SelectionPolicy policy) {

        SimulationFrame frame = new SimulationFrame();//initializare simulationframe
        this.numberOfClients = nrClients;
        this.numberOfServers = nrQueues;
        this.timeLimit=simInterval;
        this.minArrivalTime=minArrival;
        this.maxArrivalime=maxArrival;
        this.minProccesingTime=minService;
        this.maxProccesingTime=maxService;
        this.selectionPolicy=policy;
        generatedClients=new LinkedList<>();
        generateRandomClients();
        scheduler=new Scheduler(getNumberOfServers(),getNumberOfClients());//scheduler
        scheduler.changeStrategy(this.selectionPolicy);
    }

private void generateRandomClients() {
    Random random = new Random();
    for (int i = 0; i < numberOfClients; i++) {
        int clientID=i+1;
        int arrivalTime = random.nextInt((getMaxArrivalime() + 1) - getMinArrivalTime()) + getMinArrivalTime();
        int serviceTime = random.nextInt((getMaxProccesingTime() + 1) - getMinProccesingTime()) + getMinProccesingTime();
        generatedClients.add(new Client(clientID,arrivalTime,serviceTime));
    }
    Collections.sort(generatedClients);
    System.out.println(generatedClients);
}


    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            for (Client client : generatedClients) {
                if (client.getArrivalTime() == currentTime) {
                    scheduler.dispatchClient(client);
                    generatedClients.remove(client);

                }
            }
            int cnt=0;
            System.out.println("currTime = " + currentTime);
            for(Server s : scheduler.getServers()){
                System.out.println((cnt++) + " " + "timp asteptare : "+s.getTimpAsteptare() + s.getClienti());
            }
            currentTime++;
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public String toString() {
        return generatedClients.toString();
    }


}
