package client.viewModel;

import client.model.ModelFactory;

public class ViewModelFactory {
	private ChatViewModel chatViewModel;
	private LoginViewModel loginViewModel;
	private ModelFactory modelFactory;

	public ViewModelFactory(ModelFactory modelFactory) {
		this.modelFactory = modelFactory;
	}

	public ChatViewModel getChatViewModel() {
		if (chatViewModel == null)
			chatViewModel = new ChatViewModel(modelFactory.getChatModel());

		return chatViewModel;
	}

	public LoginViewModel getLoginViewModel() {
		if (loginViewModel == null)
			loginViewModel = new LoginViewModel(modelFactory.getChatModel());
		return loginViewModel;
	}
}
