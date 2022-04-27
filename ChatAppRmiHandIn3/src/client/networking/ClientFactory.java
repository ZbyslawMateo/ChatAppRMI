package client.networking;

public class ClientFactory {
    private Client client;

    public Client getClient() {
        if(client == null){
            client = new RMIClient();
        }

        return client;
    }
}
