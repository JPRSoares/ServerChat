package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.Socket;

public class Worker implements Runnable {

    private Socket clientSocket;
    private  BufferedReader input;
    private BufferedWriter output;

    public Worker(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }

    public void reading (){

        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            input.readLine();

        } catch (IOException e) {
            System.out.println(e);
        }

    }


    public void writing (){
        try {
            output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String message = new String();
            output.write(message);
            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void run() {
        reading();
        writing();



    }
}
