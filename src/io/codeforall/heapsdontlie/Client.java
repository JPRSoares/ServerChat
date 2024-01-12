package io.codeforall.heapsdontlie;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private BufferedReader userInputReader;
    private String adress ;
    private int port;

    public Client(String address, int port) {
        this.adress = address;
        this.port = port;
        try {
             socket = new Socket(adress, port);
             this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));           
            this.userInputReader = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
           e.printStackTrace();
           System.out.println("Unable to connect to the servert at" + address + ":" + port);
        }

    }

    public void sendMessages() {
        try {
            System.out.println("Connected to the chat! Type 'exit' to quit.");
            
            String messageToSend;
            while ((messageToSend = userInputReader.readLine()) != null) {  
                if ("exit".equalsIgnoreCase(messageToSend.trim())) {
                    break; 
                }
                writer.write(messageToSend);  
                writer.newLine();  
                writer.flush();  
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listenForMessages() {
        try {
            while (!socket.isClosed()) {
           String message= reader.readLine();
           if (message == null) {
            break;
           }
           System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();  
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
       listenForMessages();
    }

    public void start() {
        new Thread(this).start();
        
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8089);
        Client client2 = new Client("localhost", 8089);
        new Thread(client).start();
        new Thread(client2).start();
    }

}
