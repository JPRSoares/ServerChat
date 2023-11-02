package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int portNum = 8089;
    private ServerSocket bindSocket = null;
    private CopyOnWriteArrayList<Worker> workerList;

    public Server() {
        this.workerList = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listen(server.portNum);
    }

    private void listen(int portNum) {
        try {
            bindSocket = new ServerSocket(portNum);
            System.out.println("Server bind");
            dispatch();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void dispatch() {
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        while (true) {
            try {
                Socket clientSocket = bindSocket.accept();
                Worker worker = new Worker(clientSocket, workerList);
                workerList.add(worker);
                cachedPool.submit(worker);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
