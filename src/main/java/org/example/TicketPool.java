package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<Ticket>();
    private final int maxTicketCapacity;
    private JSONArray eventLog = new JSONArray();

    public TicketPool(int maxCapacity){
        this.maxTicketCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket){
        while (tickets.size() >= maxTicketCapacity) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.tickets.add(ticket);
        logEvent("Vendor added " + tickets.size() + " tickets.");
        notifyAll();
        System.out.println("Vendor " + Thread.currentThread().getName() + ". Ticket pool - current size - " + tickets.size());
    }

    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Ticket ticket = tickets.poll();
        logEvent("Customer " + Thread.currentThread().getName() + " received " + ticket);
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + tickets.size() + " - Ticket is - " + ticket);
        return ticket;
    }

    private void logEvent(String eventDescription) {
        JSONObject event = new JSONObject();
        event.put("event", eventDescription);
        event.put("timestamp", System.currentTimeMillis());
        eventLog.put(event);

        try (FileWriter file = new FileWriter("eventLog.json")) {
            file.write(eventLog.toString(4));
        } catch (IOException e) {
            System.out.println("Error wring log: " + e.getMessage());
        }
    }
}
