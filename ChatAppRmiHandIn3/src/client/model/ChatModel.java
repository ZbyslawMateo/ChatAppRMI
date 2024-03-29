package client.model;

import shared.util.PropertyChangeSubject;

public interface ChatModel extends PropertyChangeSubject {
    void sendMessage(String msg);
    void setUsername(String username);
}
