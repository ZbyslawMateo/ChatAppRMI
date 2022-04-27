package client.model;

import client.networking.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;

public class ChatModelManager implements ChatModel {
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private Client client;

    public ChatModelManager(Client client){
        this.client = client;
        try
        {
            this.client.startClient();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        this.client.addPropertyListener("receivedMessage", this::receivedMessage);
    }

    private void receivedMessage(PropertyChangeEvent event) {
        changeSupport.firePropertyChange("receivedMessage", event.getOldValue(), event.getNewValue());
    }

    @Override
    public void addPropertyListener(PropertyChangeListener listener){
        changeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyListener(String eventName, PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removePropertyListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyListener(String eventName, PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(eventName, listener);
    }

    @Override
    public void sendMessage(String msg) {
        client.sendMessage(msg);
    }

    @Override
    public void setUsername(String username) {
        client.setUsername(username);
    }

}
