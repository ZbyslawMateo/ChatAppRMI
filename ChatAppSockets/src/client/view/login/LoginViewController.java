package client.view.login;

import client.view.ViewController;
import client.view.ViewHandler;
import client.viewModel.LoginViewModel;
import client.viewModel.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginViewController implements ViewController
{
  @FXML private TextField input;
  @FXML private Button logIn;

  private ViewHandler viewHandler;
  private LoginViewModel vm;

  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    this.viewHandler = vh;
    this.vm =vmf.getLoginViewModel();

	input.textProperty().bindBidirectional(vm.inputProperty());
  }

    public void onSubmit(javafx.event.ActionEvent event) {
      vm.onSubmit();
      viewHandler.openChatView();
    }
}
