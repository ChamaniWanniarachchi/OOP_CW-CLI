package org.example;

public class Customer implements Runnable{
    private int customerId;
    private int quantity;
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < quantity; i++) {
                Ticket ticket = ticketPool.removeTicket();
                System.out.println("Customer " + Thread.currentThread().getName() + " retrieved " + i + " tickets.");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Process was interrupted." + e.getMessage());
        }
    }
}
