# Multi-Client Server Communication System

This Java application demonstrates a multi-client server communication system where multiple clients can connect to a server, send messages, and receive individual responses concurrently.

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Implementation Details](#implementation-details)
7. [Challenges Faced](#challenges-faced)
8. [License](#license)

## Overview
This system consists of two components:
- **Server**: Listens for incoming connections from multiple clients and handles each connection in a separate thread.
- **Client**: Connects to the server, sends messages, and receives responses.

The server supports multiple clients by creating a new thread for each client connection, enabling concurrent communication. All client messages are logged for future reference.

## Features
- **Multi-client support**: The server can handle multiple clients concurrently using threading.
- **Client-specific communication**: Each client can send a unique message to the server and receive an individualized response.
- **Graceful error handling**: The server handles client disconnections smoothly without crashing.
- **Logging functionality**: The server logs each client message in a file (`server.log`).
- **Interactive client**: The client provides an interactive interface to send and receive messages.

## Requirements
- Java 8 or higher
- A command-line interface (CLI) or IDE for compiling and running Java code

## Installation
1. Clone or download the repository.
2. Navigate to the directory containing `Server.java` and `Client.java`.
3. Compile the Java files:
   ```bash
   javac Server.java Client.java
