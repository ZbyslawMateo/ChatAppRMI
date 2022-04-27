package server.model;

import shared.networking.ClientCallback;
import shared.transferobject.Message;
import shared.util.PropertyChangeSubject;

public interface ChatModel extends PropertyChangeSubject {
  void sendMessage(Message msg);
  void registerClient(ClientCallback client);
}
