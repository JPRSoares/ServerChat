package io.codeforall.heapsdontlie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    int portNum = 8089;
    private ServerSocket bindSocket = null;

    public static void main(String[] args) {

        Server server = new Server();
        server.listen(server.portNum);

    }

    private void listen(int portNum) {

        try {
           bindSocket = new ServerSocket(portNum);
            System.out.println(" Server bind");
            dispatch(bindSocket);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void dispatch ( ServerSocket bindSocket){

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        while (true) {
            try{
                Worker worker =new  Worker(bindSocket.accept());
                cachedPool.submit(worker);

            } catch (IOException e){
                System.out.println(e);
            }
        }

    }

}
