package client.networking;

import shared.util.PropertyChangeSubject;

import java.rmi.RemoteException;

public interface Client extends PropertyChangeSubject {
    void sendMessage(String msg);
    void setUsername(String username);
    void startClient() throws RemoteException;
}
