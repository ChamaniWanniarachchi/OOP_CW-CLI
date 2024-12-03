package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration() {
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        totalTickets = promptForPositiveInt (scanner, "Enter total number of tickets: ");
        totalTickets = Integer.parseInt(scanner.nextLine());
        ticketReleaseRate = promptForPositiveInt (scanner, "Enter ticket release date (yyyy-mm-dd): ");
        ticketReleaseRate = Integer.parseInt(scanner.nextLine());
        customerRetrievalRate = promptForPositiveInt (scanner, "Enter customer retrieval rate: ");
        customerRetrievalRate = Integer.parseInt(scanner.nextLine());
        maxTicketCapacity = promptForPositiveInt (scanner, "Enter maximum ticket capacity: ");
        maxTicketCapacity = Integer.parseInt(scanner.nextLine());

        System.out.println("\nConfiguration successful!");
        System.out.println("Configuration details are as follows:");
        System.out.println("Total tickets: " + totalTickets);
        System.out.println("Ticket release date: " + ticketReleaseRate);
        System.out.println("Customer retrieval rate: " + customerRetrievalRate + "ms");
        System.out.println("Maximum ticket capacity: " + maxTicketCapacity);
    }

    private int promptForPositiveInt(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be a positive integer. PLease try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }
    }

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
