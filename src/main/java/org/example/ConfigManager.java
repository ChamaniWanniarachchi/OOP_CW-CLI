package org.example;

import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigManager {
    private Configuration configuration;

    public ConfigManager() {
        this.configuration = new Configuration();
    }

    public void manageConfiguration() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to configure the settings manually? (yes/no): ");
        String configureChoice = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(configureChoice)) {
            configuration.initialize(); // Prompt user to input configuration manually
            saveConfiguration(configuration); // Save the configuration to the file
        } else if ("no".equals(configureChoice)) {
            System.out.println("Do you want to load the configuration from the file? (yes/no): ");
            String loadChoice = scanner.nextLine().trim().toLowerCase();

            if ("yes".equals(loadChoice)) {
                if (!loadConfiguration()) {
                    System.out.println("Failed to load configuration. Process stopped.");
                    System.exit(0); // Stop the process
                }
            } else {
                System.out.println("Process stopped.");
                System.exit(0); // Stop the process
            }
        } else {
            System.out.println("Invalid input. Process stopped.");
            System.exit(0); // Stop the process
        }
    }

    public boolean loadConfiguration() {
        try (FileReader reader = new FileReader("config.json")) {
            StringBuilder content = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                content.append((char) i);
            }

            JSONObject jsonObject = new JSONObject(content.toString());
            configuration = new Configuration(
                    jsonObject.getInt("totalTickets"),
                    jsonObject.getInt("ticketReleaseRate"),
                    jsonObject.getInt("customerRetrievalRate"),
                    jsonObject.getInt("maxTicketCapacity")
            );

            System.out.println(new StringBuilder().append("Total tickets: ").append(jsonObject.getInt("totalTickets")));
            System.out.println(new StringBuilder().append("Ticket Release Rate: ").append(jsonObject.getInt("ticketReleaseRate")));
            System.out.println(new StringBuilder().append("Customer Retrieval Rate: ").append(jsonObject.getInt("customerRetrievalRate")));
            System.out.println(new StringBuilder().append("Maximum Ticket Capacity: ").append(jsonObject.getInt("maxTicketCapacity")));

            System.out.println("Configuration loaded successfully.");
            return true;
        } catch (IOException | org.json.JSONException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return false;
        }
    }

    public void saveConfiguration(Configuration config) {
        try (FileWriter file = new FileWriter("config.json")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("totalTickets", config.getTotalTickets());
            jsonObject.put("ticketReleaseRate", config.getTicketReleaseRate());
            jsonObject.put("customerRetrievalRate", config.getCustomerRetrievalRate());
            jsonObject.put("maxTicketCapacity", config.getMaxTicketCapacity());

            file.write(jsonObject.toString());
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving the configuration: " + e.getMessage());
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
