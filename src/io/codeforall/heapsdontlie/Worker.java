package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Worker implements Runnable {

    private final Socket clientSocket;
    private final List<Worker> workerList;

    public Worker(Socket clientSocket, List<Worker> workerList) {
        this.clientSocket = clientSocket;
        this.workerList = workerList;
    }

    private String reading() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return input.readLine();
    }

    public void writing(String message) throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        output.write(message);
        output.flush();
    }

    public void broadcast(String message) {
        for (Worker worker : workerList) {
            try {
                if (!worker.clientSocket.isClosed()) {
                    worker.writing(message + "\n");
                }
            } catch (IOException e) {
                System.out.println("Error broadcasting to worker: " + e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!clientSocket.isClosed()) {
                String clientMessage = reading();
                if (clientMessage == null) {
                    break;
                }
                broadcast(clientMessage);
            }
        } catch (IOException e) {
            System.out.println("Worker run: " + e.getMessage());
        } finally {
            cleanup();
        }
    }

    private void cleanup() {
        workerList.remove(this);
        try {
            if (!clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing worker socket: " + e.getMessage());
        }
    }
}
