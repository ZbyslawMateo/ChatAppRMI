package server.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChatModelManager implements ChatModel {
    private PropertyChangeSupport changeSupport;

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
}
