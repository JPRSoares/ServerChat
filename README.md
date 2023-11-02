# ServerChat

# Java Web Server

This repository contains a simple multithreaded web server implementation in Java. The server is designed to handle multiple client connections concurrently, allowing clients to send messages that are broadcast to all connected clients.

## Overview

The project is split into two main components:

- `Server`: This class sets up a server on a specified port and listens for incoming client connections. For each connection, it spawns a new `Worker` thread to handle communication.

- `Worker`: This runnable class handles the input and output streams for each client socket. It reads messages from the client and broadcasts them to all connected clients using a shared `CopyOnWriteArrayList`.

## How to Run

To run the server, you need to have Java installed on your machine. Clone the repository and compile the source files, then execute the `Server` class:

```bash
$ javac io/codeforall/heapsdontlie/Server.java io/codeforall/heapsdontlie/Worker.java
$ java io.codeforall.heapsdontlie.Server

Once the server is running, you can connect to it using any TCP client (e.g., netcat, telnet). For example, using netcat:
$ nc localhost 8089

**Features**
Multi-threaded server handling.
Easy to scale with Executors.newCachedThreadPool().
Shared message broadcasting to all clients.
Graceful handling of client disconnections.
Contribute
Contributions to this project are welcome! Here's how you can contribute:

**Fork the repository.**

Create a new branch for your feature (git checkout -b feature/fooBar).
Commit your changes (git commit -am 'Add some fooBar').
Push to the branch (git push origin feature/fooBar).
Create a new Pull Request.
