package org.example.BusinessLogic;

import org.example.Model.Client;
import org.example.Model.Server;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortestTime implements Strategy{
    @Override
    public void addClient(List<Server> servers, Client client) {//la care lista adaug client
        int min=servers.getFirst().getTimpAsteptare().get();
        HashMap<Integer, Server> coziScurte=new HashMap<>(servers.size());
        for(Server server:servers) {
            if(server.getTimpAsteptare().get()<=min){
                min=server.getTimpAsteptare().get();
                coziScurte.put(min,server);
            }
        }
        coziScurte.get(min).addTask(client);
    }
}