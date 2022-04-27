package server.networking;

import shared.transferobject.Message;
import shared.transferobject.Request;
import shared.transferobject.RequestType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable {
    private Socket connectionSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Pool pool;

    public ServerSocketHandler(Socket socket, Pool pool) {
        this.connectionSocket = socket;
        this.pool = pool;

        try{
            out = new ObjectOutputStream(connectionSocket.getOutputStream());
            in = new ObjectInputStream(connectionSocket.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                // Getting a Request object from client.
                Request request = (Request) in.readObject();
                RequestType requestType = request.getType();

                // Checking for the Request Type.
                switch (requestType){
                    case sendMessage -> {
                        // Getting the Message object from Request and call the pool.broadcast to broadcast the message to all the clients.
                        Message msg = (Message) request.getArg();
                        pool.broadcast(msg);
                    }
                }

            }
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(Message msg){
        try {
            // Wrapping the Message object in t o Request Object and sending it.
            out.writeObject(new Request(RequestType.sendMessage, msg));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
