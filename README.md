# Java Web Server Chat

This repository contains a simple multithreaded web server implementation in Java. The server is designed to handle multiple client connections concurrently, allowing clients to send and receive messages in real-time. This is a basic implementation that can be extended with additional features such as user authentication, persistent message history, and a graphical user interface.

## Overview

The project consists of the following components:

- `Server`: Listens for incoming connections and spawns a new thread for each client.
- `Client`: Connects to the server and handles sending and receiving messages.
- `Worker`: Manages the client's input and output streams, broadcasting messages to all connected clients.

## How to Run

Ensure you have Java installed on your machine. Compile the source files and run the server:

```bash
javac Server.java Client.java
java Server
```

The server will start and listen for client connections on the default port (8089).

To connect clients, open separate terminal windows and run:

```bash
java Client
```

Each client will be prompted to enter messages which will then be broadcast to all connected clients.

## Features

- Multi-threaded server handling multiple client connections.
- Client-to-client communication via server broadcasting.
- Command-line interface for client interactions.
- Graceful handling of client disconnections.

## Contributing to Server Chat

Contributions are welcome. Here's how you can contribute:

1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature/fooBar`).
3. Commit your changes (`git commit -am 'Add some fooBar'`).
4. Push to the branch (`git push origin feature/fooBar`).
5. Create a new Pull Request.

## Contact

If you want to contact me you can reach me at <soareswebmarketing@gmail.com>.

## License

This project is licensed under the [MIT License](LICENSE.md) - see the LICENSE file for details.
