package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class ShortestQueue implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client client) {
        int min=servers.getFirst().getClienti().size();
        HashMap<Integer, Server> coziScurte= new HashMap<>(servers.size());
        for(Server server:servers) {
            if(server.getClienti().size()<=min){
                min=server.getClienti().size();
                coziScurte.put(min,server);
            }
        }

        coziScurte.get(min).addTask(client);
    }
}
