package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TimeClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8081;

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            final int clientId = i;  // Create a final variable to capture the current value of i
            new Thread(() -> connectToServer(clientId)).start();
        }
    }

    private static void connectToServer(int clientId) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Read and print the response from the server
            String response = in.readLine();
            System.out.println("Client " + clientId + " - Server Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
