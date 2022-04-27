package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobject.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class RMIClient implements Client, ClientCallback
{
  private String username;
  private PropertyChangeSupport support;
  private RMIServer server;

  public RMIClient()
  {
    support = new PropertyChangeSupport(this);
  }

  @Override public void sendMessage(String msg)
  {
    Message message= new Message(msg, new Date(), username);
    try
    {
      server.sendMessage(message);
    }
    catch (RemoteException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Couldn't contact the server.");
    }
  }

  @Override public void setUsername(String username)
  {
    this.username = username;
  }

  @Override public void startClient()
  {
    try
    {
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("Server");
      UnicastRemoteObject.exportObject(this, 0);
      server.registerClient(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }

  }

  @Override public void addPropertyListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void addPropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removePropertyListener(PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }

  @Override public void removePropertyListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public void update(Message msg) throws RemoteException
  {
    support.firePropertyChange("receivedMessage",null,msg);
  }
}
