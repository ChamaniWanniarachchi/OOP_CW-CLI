package org.example;

import java.util.Scanner;

public class Configuration {
    // Declare instance variables for ticket system configuration
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Default constructor
    public Configuration() {}

    // Parameterized constructor to initialize the configuration with specific values
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Method to initialize configuration by prompting the user for input
    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for each configuration parameter, ensuring positive integer inputs
        totalTickets = promptForPositiveInt (scanner, "Enter total number of tickets: ");
        ticketReleaseRate = promptForPositiveInt (scanner, "Enter ticket release rate: ");
        customerRetrievalRate = promptForPositiveInt (scanner, "Enter customer retrieval rate: ");
        maxTicketCapacity = promptForPositiveInt (scanner, "Enter maximum ticket capacity: ");

        // Output the successful configuration and the details
        System.out.println("\nConfiguration successful!");
        System.out.println("Configuration details are as follows:");
        System.out.println("Total tickets: " + totalTickets);
        System.out.println("Ticket release rate: " + ticketReleaseRate + "ms");
        System.out.println("Customer retrieval rate: " + customerRetrievalRate + "ms");
        System.out.println("Maximum ticket capacity: " + maxTicketCapacity);
    }

    //method to prompt the user for a positive integer
    private int promptForPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) { // Check if the input is an integer
                value = scanner.nextInt();
                if (value > 0) { // Ensure the integer is positive
                    return value;
                } else {
                    System.out.println("Value must be a positive integer. PLease try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    // Getters
    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
