package org.example;

public class Ticket {
    // Instance variables to hold ticket information
    private int ticketId;
    private String eventName;
    private int ticketPrice;

    // Constructor to initialize a Ticket object with specific values
    public Ticket(int ticketId, String eventName, int ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    // Getters
    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    // Override of the toString() method to return a string representation of the Ticket object
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
