package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {

    private final Socket clientSocket;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String reading() {
        String clientMessage = "";
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        return clientMessage;
    }

    public void writing(String serverResponse) {
        try {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            output.write(serverResponse);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String clientMessage = reading();
        while (true) {
            {
                int SERVER_PORT = 8089;
                String SERVER_ADDRESS = "localhost";
                writing(clientMessage + "\n");

            }
        }

    }

}