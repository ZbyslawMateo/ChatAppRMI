package server.model;

import shared.networking.ClientCallback;
import shared.transferobject.Message;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ChatModelManager implements ChatModel {
    private PropertyChangeSupport changeSupport;
     private List<ClientCallback> clients;

     public ChatModelManager()
     {
         clients = new ArrayList<>();
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

    @Override public void sendMessage(Message msg)
    {
        for (ClientCallback client: clients)
        {
            try
            {
                client.update(msg);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override public void registerClient(ClientCallback client)
    {
        clients.add(client);
    }
}
