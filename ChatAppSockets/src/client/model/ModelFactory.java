package client.model;

import client.networking.Client;

public class ModelFactory {
    private ChatModel chatModel;
    public Client client;

    public ModelFactory(Client client){
        this.client = client;
    }

    public ChatModel getChatModel(){
        if(chatModel == null){
            chatModel = new ChatModelManager(client);
        }

        return chatModel;
    }
}
