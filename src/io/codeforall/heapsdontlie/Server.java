package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    int portNum = 8089;
    private ServerSocket bindSocket = null;
    private CopyOnWriteArrayList list;

    public static void main(String[] args) {
        Server server = new Server();
        server.listen(server.portNum);
        CopyOnWriteArrayList list = new CopyOnWriteArrayList<>();
    }

    private void listen(int portNum) {
        try {
            bindSocket = new ServerSocket(portNum);
            System.out.println("Server bind");
            dispatch(bindSocket);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void dispatch(ServerSocket bindSocket) {
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        while (true) {
            try {
                Worker worker = new Worker(bindSocket.accept());
                cachedPool.submit(worker);
            } catch (IOException e) {
                System.out.println(e);
            }

        }

    }

}