package org.example;

// Instance variables to hold vendor information and ticket release details
public class Vendor implements Runnable{
    private int vendorId;
    private int totalTickets;
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    // Constructor to initialize the Vendor
    public Vendor(int totalTickets, TicketPool ticketPool, int ticketReleaseRate) {
        this.totalTickets = totalTickets;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // The run method for the thread (defined by the Runnable interface)
    @Override
    public void run() {
        try {
            // Loop to add tickets to the pool
            for (int i = 1; i < totalTickets; i++) {
                // Create a new ticket with a unique ticketId, an event name, and ticket price
                Ticket ticket = new Ticket(i, "Simple Event", 1000);
                ticketPool.addTicket(ticket);
                // Sleep to simulate ticket release rate
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted." + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
