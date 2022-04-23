package server;

import server.networking.SocketServer;

public class
RunServer {
    public static void main(String[] args) {

        SocketServer server = new SocketServer();

        server.start();
    }
}
