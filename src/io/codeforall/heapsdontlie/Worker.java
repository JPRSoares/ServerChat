package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {

    private Socket clientSocket;
    private BufferedReader input;
    private BufferedWriter output;
    private final String SERVER_ADDRESS = "localhost";
    private final int SERVER_PORT = 8089;  // Presumi que o Worker se comunica com o Server na mesma porta. Se não for o caso, mude o valor.

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String reading() {
        String clientMessage = "";
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientMessage = input.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }
        return clientMessage;
    }

    public void writing(String serverResponse) {
        try {
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            output.write(serverResponse);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        String clientMessage = reading();

        // Send client's message to the Server and get the response
        try (Socket serverSocket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            
            serverOut.write(clientMessage + "\n");  // Remember to add newline since we are using readLine
            serverOut.flush();
            
            String serverResponse = serverIn.readLine();
            writing(serverResponse);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}