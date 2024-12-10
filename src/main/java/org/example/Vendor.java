package org.example;

public class Vendor implements Runnable{
    private int vendorId;
    private int totalTickets;
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(int totalTickets, TicketPool ticketPool, int ticketReleaseRate) {
        this.totalTickets = totalTickets;
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < totalTickets; i++) {
                Ticket ticket = new Ticket(i, "Simple Event", 1000);
                ticketPool.addTicket(ticket);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted." + e.getMessage());
        }
    }
}
