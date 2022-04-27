package server.networking;

import server.model.ChatModelManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobject.Message;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer
{

  private final ChatModelManager modelManager;

  public RMIServerImpl(ChatModelManager modelManager) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this,0);
    this.modelManager = modelManager;
  }

  public void startServer()
      throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("Server", this);
    System.out.println("Starting server...");
  }

  @Override public void sendMessage(Message msg) throws RemoteException
  {
    modelManager.sendMessage(msg);

  }

  @Override public void registerClient(ClientCallback client) throws RemoteException
  {
    modelManager.registerClient(client);
  }
}
