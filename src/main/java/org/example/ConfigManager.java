package org.example;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigManager {
    // Declare the Configuration object that holds the current configuration settings
    private Configuration configuration;

    // Constructor initializes the Configuration object
    public ConfigManager() {
        this.configuration = new Configuration();
    }

    // Method to manage configuration by allowing the user to either input manually or load from a file
    public void manageConfiguration() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user whether they want to configure settings manually
        System.out.println("Do you want to configure the settings manually? (yes/no): ");
        String configureChoice = scanner.nextLine().trim().toLowerCase();

        // If user chooses 'yes', prompt for manual configuration input and save it
        if ("yes".equals(configureChoice)) {
            configuration.initialize(); // Prompt user to input configuration manually
            saveConfiguration(configuration);
            // If user chooses 'no', ask if they want to load configuration from a file
        } else if ("no".equals(configureChoice)) {
            System.out.println("Do you want to load the configuration from the file? (yes/no): ");
            String loadChoice = scanner.nextLine().trim().toLowerCase();

            // If user chooses 'yes', attempt to load configuration from file
            if ("yes".equals(loadChoice)) {
                if (!loadConfiguration()) {
                    System.out.println("Failed to load configuration. Process stopped.");
                    System.exit(0); // Stop the process
                }
            // If user chooses 'no', stop the process
            } else {
                System.out.println("Process stopped.");
                System.exit(0); // Stop the process
            }
        // If the input is neither 'yes' nor 'no', stop the process
        } else {
            System.out.println("Invalid input. Process stopped.");
            System.exit(0); // Stop the process
        }
    }

    // Method to load configuration from a JSON file
    public boolean loadConfiguration() {
        try (FileReader reader = new FileReader("config.json")) { // Use try-with-resources to automatically close the file reader
            StringBuilder content = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) { // Read file character by character
                content.append((char) i);
            }

            // Parse the JSON content into a JSONObject
            JSONObject jsonObject = new JSONObject(content.toString());
            // Initialize the Configuration object with values from the JSON file
            configuration = new Configuration(
                    jsonObject.getInt("totalTickets"),
                    jsonObject.getInt("ticketReleaseRate"),
                    jsonObject.getInt("customerRetrievalRate"),
                    jsonObject.getInt("maxTicketCapacity")
            );

            // Output the loaded configuration values to the console
            System.out.println(new StringBuilder().append("Total tickets: ").append(jsonObject.getInt("totalTickets")));
            System.out.println(new StringBuilder().append("Ticket Release Rate: ").append(jsonObject.getInt("ticketReleaseRate")));
            System.out.println(new StringBuilder().append("Customer Retrieval Rate: ").append(jsonObject.getInt("customerRetrievalRate")));
            System.out.println(new StringBuilder().append("Maximum Ticket Capacity: ").append(jsonObject.getInt("maxTicketCapacity")));

            System.out.println("Configuration loaded successfully.");
            return true;
        } catch (IOException | org.json.JSONException e) { // Handle exceptions for IO errors or JSON parsing errors
            System.out.println("Error loading configuration: " + e.getMessage());
            return false;
        }
    }

    // Method to save the configuration settings to a JSON file
    public void saveConfiguration(Configuration config) {
        try (FileWriter file = new FileWriter("config.json")) {// Use try-with-resources to automatically close the file writer
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("totalTickets", config.getTotalTickets());
            jsonObject.put("ticketReleaseRate", config.getTicketReleaseRate());
            jsonObject.put("customerRetrievalRate", config.getCustomerRetrievalRate());
            jsonObject.put("maxTicketCapacity", config.getMaxTicketCapacity());

            file.write(jsonObject.toString()); // Write the JSON object to the file
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the configuration: " + e.getMessage());
        }
    }

    // Getter method to access the current configuration object
    public Configuration getConfiguration() {
        return configuration;
    }
}
