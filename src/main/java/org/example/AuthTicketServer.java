package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class AuthTicketServer {
    static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, String> clientTickets = new HashMap<>();

    public static void main(String[] args) {
        // Add some user credentials (username, password)
        userCredentials.put("user1", hashPassword("password1"));
        userCredentials.put("user2", hashPassword("password2"));
        userCredentials.put("user3", hashPassword("password3"));


        // Simulate server running
        System.out.println("Authentication and Ticket Server is running.");

        // Example: Simulate client authentication
        String username = "user1";
        String password = "password1";
        if (authenticateUser(username, password)) {
            String ticket = generateTicket(username);
            System.out.println("Authentication successful. Issued ticket: " + ticket);
        } else {
            System.out.println("Authentication failed.");
        }

        runServer();
    }

    private static void runServer() {
        // Infinite loop to keep the server running
        while (true) {
            // You can add any periodic tasks or checks here

            try {
                // Sleep for a while to avoid high CPU usage in the loop
                Thread.sleep(5000); // Adjust as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static boolean authenticateUser(String username, String password) {
        // Check if the provided credentials are valid
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(hashPassword(password));
    }

    private static String generateTicket(String username) {
        // Generate a secure ticket for the authenticated user
        String ticket = hashPassword(username + System.currentTimeMillis());
        clientTickets.put(username, ticket);
        return ticket;
    }

    private static String hashPassword(String password) {
        // Hash the password using SHA-256 for basic security
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for (byte hashedByte : hashedBytes) {
                stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

