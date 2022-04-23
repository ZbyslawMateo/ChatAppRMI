package client.view.chat;

import client.view.ViewController;
import client.view.ViewHandler;
import client.viewModel.ChatViewModel;
import client.viewModel.ViewModelFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatViewController implements ViewController
{
    @FXML private ListView chatList;
    @FXML private TextField input;
    @FXML private Button submit;
    @FXML private Button logOf;

    private ViewHandler viewHandler;
    private ChatViewModel vm;

    @Override public void init(ViewHandler vh, ViewModelFactory vmf)
    {
        this.viewHandler = vh;
        this.vm = vmf.getChatViewModel();
    }

    public void onSubmit(javafx.event.ActionEvent event) {
        vm.onSubmit(input.getText());
        input.textProperty().bindBidirectional(vm.inputProperty());
        chatList.setItems(vm.getItems());
    }

    public void onLogOf(javafx.event.ActionEvent event) {
        viewHandler.openLoginView();
    }
}
