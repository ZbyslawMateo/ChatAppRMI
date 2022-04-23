package client.viewModel;

import client.model.ChatModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel {
	private StringProperty input;
	private ChatModel model;

	LoginViewModel(ChatModel model) {
		input = new SimpleStringProperty();
		this.model = model;
	}

	public void onSubmit() {
		model.setUsername(input.getValue());
	}

	public StringProperty inputProperty() {
		return input;
	}
}
