package com.Monk.Client;

/**
 * Interface for generating clients
 */
public class ClientGenerator {

    private int PORT;
    private String HOST;

    public ClientGenerator(String host, int port){
        HOST = host;
        PORT = port;
    }

    /**
     * Generate clients that send WRITE request
     * @param clients number of clients to be generated
     */
    public void generateWriters(int clients) {
        for(int i=0;i<clients;i++){
            new Client(HOST, PORT, false, true).start();
        }
    }

    /**
     * Generate clients that send READ request
     * @param clients number of clients to be generated
     */
    public void generateReaders(int clients) {
        for(int i=0;i<clients;i++){
            new Client(HOST, PORT, true, false).start();
        }
    }

}
