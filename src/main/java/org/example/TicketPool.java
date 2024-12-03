package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.events.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Integer> tickets = new LinkedList<Integer>();
    private final int maxTicketCapacity;
    private JSONArray eventLog = new JSONArray();

    public TicketPool(int maxCapacity){
        this.maxTicketCapacity = maxCapacity;
    }

    public synchronized void addTicket(int ticket){
        while (tickets.size() >= maxTicketCapacity) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tickets.add(ticket);
        logEvent("Vendor added " + tickets.size() + "tickets.");
        notifyAll();
    }

    public synchronized void removeTicket() {
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Integer ticket = tickets.poll();
        logEvent("Customer received the ticket.");
        notifyAll();
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
