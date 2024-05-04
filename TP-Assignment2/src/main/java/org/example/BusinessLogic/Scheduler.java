package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers=maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.servers=new ArrayList<>(maxNoServers);
        for(int i=0; i<this.maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            servers.add(server);
            Thread thread=new Thread(server);//dau clasa pe care vreau sa mi ruleze threadul
            thread.start();
        }


    }

    //public boolean getServerStatus(int index) {
    //  if (index >= 0 && index < servers.size()) {
    //    return servers.get(index).isBusy();
    //}
    //return false;
    //}

    public void changeStrategy(SelectionPolicy policy){

        if(policy==SelectionPolicy.SHORTEST_TIME){
            strategy=new ShortestTime();
        }
        if(policy==SelectionPolicy.SHORTEST_QUEUE) {
            strategy=new ShortestQueue();
        }

    }
    public void dispatchClient(Client c){
        strategy.addClient(servers,c);
    }

    public List<Server> getServers() {
        return servers;
    }
}