package com.Monk.Client;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client extends Thread{

    private BufferedReader in;
    private PrintWriter out;
    private boolean reads, writes;

    public Client(String host, int port, boolean reads, boolean writes) {
        Socket dataSocket;
        try {
            dataSocket = new Socket(host, port);
            InputStream is = dataSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
            OutputStream os = dataSocket.getOutputStream();
            out = new PrintWriter(os, true);
        } catch (IOException e) {}
        System.out.println("Connection to " + host + " established");
        this.reads = reads;
        this.writes = writes;
    }

    @Override
    public void run() {
        if(reads) readRandom();
        else if(writes) writeRandom();
    }

    /**
     * Sends a READ request of a random ID selected from RouteConstants
     * @see RouteConstants
     */
    private void readRandom() {
        Random random = new Random();
        int selectedID = random.nextInt(RouteConstants.RouteID.length - 1);
        read(RouteConstants.RouteID[selectedID]);
    }

    /**
     * Sends a WRITE request with a random route  from RouteConstants
     * @see RouteConstants
     */
    private void writeRandom() {
        Random random = new Random();
        int route = random.nextInt(9);
        int status = random.nextInt(1);
        write(RouteConstants.RouteID[route], RouteConstants.Status[status], RouteConstants.Datetime[route]);
    }

    /**
     * Sends a READ request to the server
     * @param routeID the ID to be searched in the database
     */
    public void read(String routeID) {
        out.println("READ " + routeID);
        waitReply();
    }

    /**
     * Sends a WRITE request to the server
     * @param routeID the ID of the route to be registered
     * @param status the status of the flight (departed/arrived)
     * @param datetime the time of the departure/arrival
     */
    public void write(String routeID, String status, String datetime) {
        out.println("WRITE " + routeID + " " + status + " " + datetime);
        waitReply();
    }

    /**
     * Sends a MODIFY request to the server
     * @param routeID the ID of the route to be modified
     * @param newRouteID the new ID of the route
     * @param status the new status of the route
     * @param datetime the new datetime of the route
     */
    public void modify(String routeID, String newRouteID, String status, String datetime) {
        out.println("MODIFY " + routeID + " " + newRouteID + " " + status + " " + datetime);
        waitReply();
    }

    /**
     * Sends a DELETE request to the server
     * @param routeID the ID of the route to be deleted
     */
    public void delete(String routeID) {
        out.println("DELETE " + routeID);
        waitReply();
    }

    /**
     * Helper method that waits until a reply from the server is available and prints it
     */
    private void waitReply(){
        try {
            while(!in.ready()){}
            String reply = in.readLine();
            System.out.println(reply);
        } catch (IOException e) {}
    }
}
