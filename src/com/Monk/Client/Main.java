package com.Monk.Client;

import java.util.Scanner;

public class Main {

    private static final int PORT = 1000;
    private static final String HOST = "localhost";
    private static final int WRITE_CLIENTS = 20;

    public static void main(String[] args) {
        System.out.println("Select option: ");
        System.out.println("1.Send READ request");
        System.out.println("2.Send WRITE request");
        System.out.println("3.Generate clients");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        String routeID;
        switch (choice) {
            case 1:
                System.out.print("Insert route ID: ");
                routeID = input.next();
                new Client(HOST, PORT, false, false).read(routeID);
                break;
            case 2:
                System.out.print("Insert route ID: ");
                routeID = input.next();
                System.out.println();
                System.out.print("Insert route status: ");
                String status = input.next();
                System.out.println();
                System.out.print("Insert time: ");
                String time = input.next();
                System.out.println();
                new Client(HOST, PORT, false, false).write(routeID, status, time);
                break;
            case 3:
                System.out.print("Insert number of read clients to be generated: ");
                int clients = input.nextInt();
                new ClientGenerator(HOST, PORT).generateWriters(WRITE_CLIENTS);
                new ClientGenerator(HOST, PORT).generateReaders(clients);
                break;
            case 4:
                break;
            default:
                System.out.println("Please insert a legit option");
                break;
        }
    }
}
