package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Ticketing System!");

        Configuration config = new Configuration();
        config.initialize();

        TicketPool ticketPool = new TicketPool(100);

        Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate());
        Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate());

        Thread vendorThread = new Thread(vendor);
        Thread customerThread = new Thread(customer);

        try {
            vendorThread.join();
            customerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted: " + e.getMessage());
        }

        System.out.println("Initialization completed successfully! Proceeding the execution...");
    }
}