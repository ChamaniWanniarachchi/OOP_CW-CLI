package org.example;

public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < customerRetrievalRate; i++) {
                ticketPool.removeTicket();
                System.out.println("Customer retrieved a ticket.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted." + e.getMessage());
        }
    }
}
