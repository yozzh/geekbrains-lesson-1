package ru.geekbrains.classes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

class Server {
    private final ServerSocket listener;
    private final Logger log;
    private static Set<String> names = new HashSet<>();
    private static Set<DataOutputStream> clients = new HashSet<>();

    Server(int port) throws IOException {
        listener = new ServerSocket(port);
        log = Logger.getLogger("Server");
    }

    void start() throws IOException {
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
            new ClientHandler(this, socket).start();
        }
    }

    private ServerSocket getListener() {
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

    private String getMessageString(String username, String messageText) {
        ChatMessageContainer message = new ChatMessageContainer(
                MessageType.MESSAGE,
                String.format("{%s}: %s", username, messageText)
        );
        return message.toString();
    }

    private void sendServerMessage(String messageText) throws IOException {
        String formattedMessage = getMessageString("SERVER", messageText);
        for (DataOutputStream client : clients) {
            client.writeUTF(formattedMessage);
            client.flush();
        }
        log.info("Server message was sent.");
    }

    void sendUserMessage(String username, String messageText) throws IOException {
        String formattedMessage = getMessageString(username, messageText);
        for (DataOutputStream client : clients) {
            client.writeUTF(formattedMessage);
            client.flush();
        }
    }
}
