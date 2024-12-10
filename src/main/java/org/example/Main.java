package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Ticketing System!");

        ConfigManager configManager = new ConfigManager();

        // Manage configuration based on user input
        configManager.manageConfiguration();

        Configuration config = configManager.getConfiguration();

        TicketPool ticketPool = new TicketPool(config.getTotalTickets());

        Vendor[] vendors = new Vendor[10];
        Thread[] vendorThreads = new Thread[vendors.length];

        for (int i = 0; i < vendors.length; i++) {
            vendors[i] = new Vendor(25, ticketPool, 5);
            vendorThreads[i] = new Thread(vendors[i], "Vendor ID - " + i);
            vendorThreads[i].start();
        }

        Customer[] customers = new Customer[10];
        Thread[] customerThreads = new Thread[customers.length];

        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(ticketPool, 6, 5);
            customerThreads[i] = new Thread(customers[i], "Customer ID - " + i);
            customerThreads[i].start();
        }

        System.out.println("Initialization completed successfully! Proceeding with execution...");

        // Wait for all threads to complete
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }

            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted while waiting for other threads: " + e.getMessage());
        }

        System.out.println("All threads completed execution. Shutting down system...");
    }
}