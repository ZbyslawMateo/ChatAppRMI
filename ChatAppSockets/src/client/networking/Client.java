package client.networking;

import shared.util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject {
    void sendMessage(String msg);
    void setUsername(String username);
    void startClient();
}
