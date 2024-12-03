package org.example;

public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < ticketReleaseRate; i++) {
                ticketPool.addTicket(1);
                System.out.println("Vendor added a ticket.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted." + e.getMessage());
        }
    }
}
