package shared.networking;

import shared.transferobject.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote
{
  void update(Message msg) throws RemoteException;
}
