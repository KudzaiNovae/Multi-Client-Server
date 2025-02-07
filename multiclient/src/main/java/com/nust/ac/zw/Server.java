package com.nust.ac.zw;

/**
 * Multi-Client Server Implementation in Java
 * 
 * This program creates a server that can handle multiple clients concurrently
 * using threading. Each client can send a unique message, and the server will
 * respond individually to each client while logging the messages received.
 *
 * Features:
 * - Supports multiple clients using a thread pool.
 * - Logs client messages to a file.
 * - Handles client disconnections gracefully.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server class initializes a server socket and handles incoming client
 * connections using a thread pool.
 */
public class Server {
    private static final int PORT = 5000;
    private static final int THREAD_POOL_SIZE = 10;
    private static final String LOG_FILE = "server_logs.txt";

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             PrintWriter logWriter = new PrintWriter(new FileWriter(LOG_FILE, true), true)) {
            System.out.println("Server started on port " + PORT + ", waiting for clients...");
            logWriter.println(getTimestamp() + " - Server started on port " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getRemoteSocketAddress());
                logWriter.println(getTimestamp() + " - New client connected: " + clientSocket.getRemoteSocketAddress());
                threadPool.execute(new ClientHandler(clientSocket, logWriter));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            threadPool.shutdown();
        }
    }

    static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
