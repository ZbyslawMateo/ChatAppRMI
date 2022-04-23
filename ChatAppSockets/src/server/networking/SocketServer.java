package server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public void start(){
        try {
            //Creating pool and server socket
            ServerSocket serverSocket = new ServerSocket(1300);
            Pool pool = new Pool();

            while (true){
                System.out.println("Waiting for a client...");

                // Accept connection from client.
                Socket socket = serverSocket.accept();

                // Creating ServerSocketHandler for each client connection.
                ServerSocketHandler serverSocketHandler = new ServerSocketHandler(socket, pool);
                System.out.println("Client connected from " + socket.getInetAddress().getHostAddress() + " " + socket.getLocalPort());
                // Adding ServerSocketHandler to the pool.
                pool.addConnection(serverSocketHandler);

                // Starting a separate thread for the new ServerSocketHandler.
                new Thread(serverSocketHandler).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
