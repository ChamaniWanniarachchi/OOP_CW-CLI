package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    // Queue to hold tickets, ensuring thread-safe ticket access
    private final Queue<Ticket> tickets = new LinkedList<Ticket>();
    private final int maxTicketCapacity;
    // Event log to store details of ticket operations
    private JSONArray eventLog = new JSONArray();

    // Constructor
    public TicketPool(int maxCapacity){
        this.maxTicketCapacity = maxCapacity;
    }

    // Method to add a ticket to the pool
    public synchronized void addTicket(Ticket ticket){
        // If the pool is at maximum capacity, wait for space to become available
        while (tickets.size() >= maxTicketCapacity) {
            try{
                wait(); // Block the thread until tickets are removed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Add the ticket to the pool
        this.tickets.add(ticket);
        // Log the event and notify all threads waiting for tickets
        logEvent("Vendor added " + tickets.size() + " tickets.");
        notifyAll();
        System.out.println("Vendor " + Thread.currentThread().getName() + ". Ticket pool - current size - " + tickets.size());
    }

    // Method to remove a ticket from the pool
    public synchronized Ticket removeTicket() {
        // If the pool is empty, wait for tickets to be added
        while (tickets.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Remove and return a ticket from the pool
        Ticket ticket = tickets.poll();
        // Log the event and notify all threads waiting to add tickets
        logEvent("Customer " + Thread.currentThread().getName() + " received " + ticket);
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + tickets.size() + " - Ticket is - " + ticket);
        return ticket;
    }

    // Method to log events (ticket additions and removals)
    private void logEvent(String eventDescription) {
        // Create a new JSON object for the event
        JSONObject event = new JSONObject();
        event.put("event", eventDescription);
        event.put("timestamp", System.currentTimeMillis());
        eventLog.put(event);

        // Write the event log to a file (eventLog.json)
        try (FileWriter file = new FileWriter("eventLog.json")) {
            file.write(eventLog.toString(4));
        } catch (IOException e) {
            System.out.println("Error wring log: " + e.getMessage());
        }
    }
}
