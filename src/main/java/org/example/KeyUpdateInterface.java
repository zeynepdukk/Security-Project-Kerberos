package org.example;
import java.util.Scanner;
import javax.crypto.KeyAgreement;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import static org.example.AuthTicketServer.userCredentials;

public class KeyUpdateInterface {
    private static String serverPublicKey; // Simulated server's public key
    private static String serverPrivateKey; // Simulated server's private key

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simulate server running
        System.out.println("Key Update Interface is running.");

        while (true) {
            System.out.println("1. Update Server Key");
            System.out.println("2. Update Client Key");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    updateServerKey();
                    break;
                case 2:
                    updateClientKey();
                    break;
                case 3:
                    System.out.println("Exiting Key Update Interface.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void updateServerKey() {
        // Generate server's new key pair (public key and private key)
        KeyPair serverKeyPair = generateKeyPair();
        serverPublicKey = encodePublicKey(serverKeyPair.getPublic());
        serverPrivateKey = encodePrivateKey(serverKeyPair.getPrivate());

        System.out.println("Server Key Pair generated and updated securely.");
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Key size, adjust as needed
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String encodePublicKey(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    private static String encodePrivateKey(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    private static void updateClientKey() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter client username: ");
        String username = scanner.nextLine();

        // Check if the username exists in userCredentials
        if (userCredentials.containsKey(username)) {
            // Generate a new key pair for the client
            KeyPair clientKeyPair = generateKeyPair();
            String clientPublicKey = encodePublicKey(clientKeyPair.getPublic());

            // Update the client's public key in your system
            userCredentials.put(username, clientPublicKey);

            System.out.println("Client key updated successfully for user: " + username);
        } else {
            System.out.println("Username not found. Unable to update client key.");
        }
    }

}
