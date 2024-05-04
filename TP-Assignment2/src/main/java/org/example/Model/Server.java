package org.example.Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue <Client> clienti;
    private AtomicInteger timpAsteptare;//thread safe-doar ptr thread



    public AtomicInteger getTimpAsteptare() {
        return timpAsteptare;
    }

    public Server(int nrClienti){
        clienti= new LinkedBlockingQueue<>(nrClienti);//initialize max capacity for a thread queue
        timpAsteptare=new AtomicInteger(0);//se pastreaza atomicitatea si nu devine 0 pt fiecare thread
    }

    public void addTask(Client client){
        clienti.add(client);//coada iti adauga client din lobby
        timpAsteptare.addAndGet(client.getServiceTime());//incrementam timpu de asteptare
    }

    // public boolean isBusy() {
    //     return !clienti.isEmpty(); // If the server's task queue is not empty, it's considered busy
    // }
    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client client = clienti.take(); //stocam undeva clientul ca sa poti lua sleeping time
                Thread.sleep(client.getServiceTime());// Stop the thread for a time equal to task's processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Server{" +
                "clienti=" + clienti +
                ", timpAsteptare=" + timpAsteptare +
                '}';
    }
}

