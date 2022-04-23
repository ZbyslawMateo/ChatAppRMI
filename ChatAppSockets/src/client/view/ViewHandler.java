package client.view;

import client.view.chat.ChatViewController;
import client.view.login.LoginViewController;
import client.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private ViewModelFactory vmf;
  private Scene chatViewScene;
  private Scene loginViewScene;

  public ViewHandler( ViewModelFactory vmf, Stage stage){
    this.vmf = vmf;
    this.stage = stage;
  }

  public void start(){
    openLoginView();
    stage.show();
  }

  private Parent getRootByPath(String path, FXMLLoader loader){
    loader.setLocation(getClass().getResource(path));
    Parent root = null;

    try {
      root = loader.load();
    } catch (IOException e){
      e.printStackTrace();
    }

    return root;
  }

  public void openLoginView(){
    FXMLLoader loader = new FXMLLoader();
    if(loginViewScene == null){
      Parent root = getRootByPath("../view/login/LoginView.fxml", loader);
      LoginViewController controller = loader.getController();
      controller.init(this,vmf);
      loginViewScene = new Scene(root);
    }

    stage.setTitle("Login");
    stage.setScene(loginViewScene);
  }

  public void openChatView(){
    FXMLLoader loader = new FXMLLoader();

    if(chatViewScene == null){
      Parent root = getRootByPath("../view/chat/ChatView.fxml", loader);
      ChatViewController controller = loader.getController();
      controller.init(this, vmf);
      chatViewScene = new Scene(root);
    }

    stage.setTitle("Chat");
    stage.setScene(chatViewScene);
  }


}
