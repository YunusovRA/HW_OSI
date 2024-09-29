package ru.netology.server;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("New connection accepted");

                    out.println("Write your name:");
                    String name = in.readLine();
                    System.out.println("Received name: " + name);

                    out.println("Are you a child? (yes/no)");
                    String isChild = in.readLine();
                    System.out.println("Received answer: " + isChild);

                    if ("yes".equalsIgnoreCase(isChild)) {
                        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
                    } else {
                        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                    }
                } catch (IOException ex) {
                    System.out.println("Client handling exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}