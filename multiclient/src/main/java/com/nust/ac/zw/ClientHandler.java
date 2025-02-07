package com.nust.ac.zw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ClientHandler is responsible for handling communication with an individual client.
 */
class ClientHandler implements Runnable {
    private final Socket socket;
    private final PrintWriter logWriter;

    public ClientHandler(Socket socket, PrintWriter logWriter) {
        this.socket = socket;
        this.logWriter = logWriter;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            
            String message = input.readLine();
            System.out.println("Received from " + socket.getRemoteSocketAddress() + ": " + message);
            logWriter.println(Server.getTimestamp() + " - Received from " + socket.getRemoteSocketAddress() + ": " + message);
            output.println(message + "Server received: ");
        } catch (IOException e) {
            System.err.println("Client disconnected: " + socket.getRemoteSocketAddress());
            logWriter.println(Server.getTimestamp() + " - Client disconnected: " + socket.getRemoteSocketAddress());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
