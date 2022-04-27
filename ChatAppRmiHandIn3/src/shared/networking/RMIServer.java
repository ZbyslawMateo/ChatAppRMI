package shared.networking;

import shared.transferobject.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote
{
  void sendMessage(Message msg) throws RemoteException;
  void registerClient(ClientCallback client) throws RemoteException;


}
