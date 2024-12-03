package org.example;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    public void saveConfiguration (Configuration config) {
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
}
