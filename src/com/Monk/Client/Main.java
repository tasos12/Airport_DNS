package com.Monk.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static final int PORT = 1000;
    private static final String HOST = "localhost";

    public static void main(String[] args) throws IOException {

        Socket dataSocket = new Socket(HOST, PORT);
        InputStream is = dataSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        OutputStream os = dataSocket.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        System.out.println("Connection to " + HOST + " established");
        Scanner reader = new Scanner(System.in);

        while(true) {
            System.out.print("Send request: ");
            String request = reader.nextLine().trim();
            if(!request.equals("ERROR")){
                out.println(request);
                while(!in.ready()){}
                String reply = in.readLine();
                System.out.println(reply);
                //cp.processReply(reply);
            }
        }
    }
}
