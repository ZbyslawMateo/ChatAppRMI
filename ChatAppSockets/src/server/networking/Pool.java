package server.networking;

import transferobject.Message;

import java.util.ArrayList;

public class Pool {
    private ArrayList<ServerSocketHandler> list;

    public Pool(){
        list = new ArrayList<>();
    }

    public void addConnection(ServerSocketHandler ssh){
        list.add(ssh);
    }

    public synchronized void broadcast(Message msg){
        // Broadcasting the message to all the clients that have been connected to the server.
        for (ServerSocketHandler ssh : list) {
            ssh.sendMessage(msg);
        }
    }
}
