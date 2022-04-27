package client.networking;

import shared.transferobject.Message;
import shared.transferobject.Request;
import shared.transferobject.RequestType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class SocketClient implements Client{
    private String userName;
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private String ip = "localhost";

	/**
	 * Sending message to server
	 * @param msg
	 */
    @Override
    public void sendMessage(String msg) {
		// creating message object which handle formatting
		Message message = new Message(msg, new Date(), userName);

		// here we send type and message object
		try {
			sendToServer(RequestType.sendMessage, message);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void setUsername(String username) {
		this.userName = username;
    }

    @Override
    public void startClient(){
		try {
			// connecting to server with ip and port
			Socket socket = new Socket(ip, 1300);
			ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

			// calling method listenToServer in thread, so server can handle multiple listeners
			new Thread(() -> listenToServer(outToServer, inFromServer)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * Listen to server, read messages server sends
	 * @param outToServer
	 * @param inFromServer
	 */
	private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
		try {
			// runs forever so we listen forever
			while (true) {
				// this is what we receive
				Request request = (Request) inFromServer.readObject();
				// fire event so we notify client then model and then view model
				changeSupport.firePropertyChange("receivedMessage", null, request.getArg());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method sends argument and type to server
	 * @param arg
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void sendToServer(RequestType type, Object arg) throws IOException, ClassNotFoundException {
		// Connect to server
		Socket socket = new Socket(ip, 1300);
		ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());

		// send to server as a Request object
		outToServer.writeObject(new Request(type, arg));
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
}
