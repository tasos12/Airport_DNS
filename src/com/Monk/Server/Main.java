package com.Monk.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.Monk.Server.Constants.PORT;

public class Main {

    public static void main(String[] args) throws IOException {
        Database database = new Database();
        ServerSocket connectionSocket = new ServerSocket(PORT);
        while (true) {
            System.out.println("Server is listening to port: " + PORT);
            Socket dataSocket = connectionSocket.accept();
            System.out.println("Received request from " + dataSocket.getInetAddress());
            ServerThread sthread = new ServerThread(dataSocket, database);
            sthread.start();
        }
    }
}
