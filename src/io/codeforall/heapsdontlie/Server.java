2. **Server**

   O `Server` escuta por conexões, aceita-as e encaminha para o `Worker`. Como ele não está processando a mensagem ainda, vou adicionar uma lógica simples para enviar uma resposta para o `Worker`:

```java
package io.codeforall.heapsdontlie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
                Socket clientSocket = bindSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String inputLine = in.readLine();
                System.out.println("Mensagem recebida do Worker: " + inputLine);

                String response = "Resposta para: " + inputLine + "\n";  // Adding newline
                out.write(response);
                out.flush();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}