package ru.geekbrains.classes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private final ServerSocket listener;
    private final Logger log;
    private static Set<String> names = new HashSet<>();
    private static Set<DataOutputStream> clients = new HashSet<>();

    public Server(int port) throws IOException {
        listener = new ServerSocket(port);
        log = Logger.getLogger("Server");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    listener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void start() throws IOException {
        log.info("Server successful started!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String serverMessage = scanner.nextLine();
                    try {
                        sendServerMessage(serverMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        while (true) {
            Socket socket = this.getListener().accept();
            new Handler(this, socket).start();
        }
    }

    public ServerSocket getListener() {
        return this.listener;
    }

    synchronized void addUser(String name, DataOutputStream clientOut) {
        if (!names.contains(name)) {
            names.add(name);
            clients.add(clientOut);
        }
        log.info("New user has been added: " + name);
    }

    synchronized void removeUser(String name, DataOutputStream clientOut) {
        names.remove(name);
        clients.remove(clientOut);
        log.info("User has been disconnected: " + name);
    }

    void sendUserMessage(String username, String message) throws IOException {
        String formattedMessage = String.format("%s\t{%s}: %s", MessageType.MESSAGE.toString(), username, message);
        for (DataOutputStream client : clients) {
            client.writeUTF(formattedMessage);
            client.flush();
        }
    }

    void sendServerMessage(String message) throws IOException {
        String formattedMessage = String.format("%s\t{SERVER}: %s", MessageType.MESSAGE.toString(), message);
        for (DataOutputStream client : clients) {
            client.writeUTF(formattedMessage);
            client.flush();
        }
    }
}
