package com.Monk.Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;


/**
 * A thread created by dispatcher that handles the request send by the client
 *
 * @author Gkagkas Anastasios (tasosggps@gmail.com)
 * @version 1.0
 */
public class ServerThread extends Thread {

    private Database        database;
    private Socket          dataSocket;
    private InputStream     is;
    private BufferedReader  in;
    private OutputStream    os;
    private PrintWriter     out;


    public ServerThread(Socket socket,Database database) {
        dataSocket = socket;
        this.database = database;
        try {
            is = dataSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
            os = dataSocket.getOutputStream();
            out = new PrintWriter(os,true);
        }
        catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }

    /**
     * Starts processing the request with the route protocol and send a reply when it is done
     */
    @Override
    public void run() {
        String inmsg, outmsg;

        try {
            RouteProtocol app = new RouteProtocol(database);
            inmsg = in.readLine();
            outmsg = app.processRequest(inmsg);
            while(!outmsg.equals(Constants.EXIT)) {
                out.println(outmsg);
                inmsg = in.readLine();
                outmsg = app.processRequest(inmsg);
            }
            dataSocket.close();
            System.out.println("Data socket closed");
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }
}
