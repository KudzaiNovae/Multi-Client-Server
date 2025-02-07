package com.nust.ac.zw;

/**
 * Client class connects to the server and allows the user to send messages.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            
            System.out.print("Enter message for server: ");
            String message = userInput.readLine();
            output.println(message);
            System.out.println("Server response: " + input.readLine());
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
