package ru.netology.client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String hostname = "netology.homework";
        int port = 8080;

        try (Socket socket = new Socket(hostname, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {

            // Читаем запрос имени от сервера
            System.out.println(in.readLine());
            String name = userInput.readLine();
            out.println(name);

            // Читаем вопрос о возрасте от сервера
            System.out.println(in.readLine());
            String isChild = userInput.readLine();
            out.println(isChild);

            // Читаем ответ от сервера
            String response = in.readLine();
            System.out.println(response);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}