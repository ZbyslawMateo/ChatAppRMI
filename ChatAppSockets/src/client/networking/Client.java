package client.networking;

import util.PropertyChangeSubject;

public interface Client extends PropertyChangeSubject {
    void sendMessage(String msg);
    void setUsername(String username);
    void startClient();
}
