import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private LocalDate ticketReleaseDate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration() {
    }

    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        totalTickets = promptForPositiveInt (scanner, "Enter total number of tickets: ");
        ticketReleaseDate = promptForValidDate (scanner, "Enter ticket release date (yyyy-mm-dd): ");
        customerRetrievalRate = promptForPositiveInt (scanner, "Enter customer retrieval rate: ");
        maxTicketCapacity = promptForPositiveInt (scanner, "Enter maximum ticket capacity: ");

        System.out.println("\nConfiguration successful!");
        System.out.println("Configuration details are as follows:");
        System.out.println("Total tickets: " + totalTickets);
        System.out.println("Ticket release date: " + ticketReleaseDate);
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

    private LocalDate promptForValidDate(Scanner scanner, String prompt) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.println(prompt);
            String input = scanner.next();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-mm-dd.");
            }
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public LocalDate getTicketReleaseDate() {
        return ticketReleaseDate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
