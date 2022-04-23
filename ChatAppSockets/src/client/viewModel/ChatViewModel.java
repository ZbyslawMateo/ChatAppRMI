package client.viewModel;

import client.model.ChatModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;

public class ChatViewModel {
	private ObservableList<String> chatList;
	private StringProperty input;
	private ChatModel model;

	ChatViewModel(ChatModel model) {
		this.model = model;

		input = new SimpleStringProperty();
		chatList = FXCollections.observableArrayList();

		model.addPropertyListener("receivedMessage", this::receivedMessage);
	}

	private void receivedMessage(PropertyChangeEvent event) {
		Platform.runLater(
			() -> {
				chatList.add((event.getNewValue()).toString());
			}
		);
	}

	public void onSubmit(String message) {
		model.sendMessage(message);
		input.setValue("");
	}

	public StringProperty inputProperty() {
		return input;
	}

	public ObservableList getItems() {
		return chatList;
	}
}
