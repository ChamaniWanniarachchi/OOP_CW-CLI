package org.example;

public class Customer implements Runnable{
    // Instance variables to hold customer-related information
    private int customerId;
    private int quantity;
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    // Constructor to initialize the Customer
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    // The run method for the thread (defined by the Runnable interface)
    @Override
    public void run() {
        try {
            // Loop to retrieve the specified quantity of tickets from the ticket pool
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
