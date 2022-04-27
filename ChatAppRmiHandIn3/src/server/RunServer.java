package server;

import server.model.ChatModelManager;
import server.networking.RMIServerImpl;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

public class
RunServer {
    public static void main(String[] args)
        throws AlreadyBoundException, RemoteException
    {

        RMIServerImpl ss = new RMIServerImpl(new ChatModelManager());
        ss.startServer();
    }
}
