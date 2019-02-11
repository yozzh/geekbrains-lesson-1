package ru.geekbrains.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientHandler extends Thread {
    private final Logger log;
    private Socket socket;
    private Server server;
    private String username;
    private DataInputStream in;
    private DataOutputStream out;

    ClientHandler(Server server, Socket socket) throws IOException {
        log = Logger.getLogger("ClientHandler");
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        log.info("New connection has been started!");
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                this.sendMessage(new ChatMessageContainer(
                    MessageType.GET_NAME
                ));
                ChatMessageContainer userMessage = ChatMessageContainer.createFromString(in.readUTF());
                this.setUsername(userMessage.getContent());
                break;
            }

            this.sendMessage(new ChatMessageContainer(
                MessageType.READY_FOR_MESSAGING
            ));

            while (true) {
                ChatMessageContainer message = ChatMessageContainer.createFromString(in.readUTF());
                if (message.isEmpty()) {
                    return;
                }
                log.info("Message from client received!");

                server.sendUserMessage(username, message.getContent());
            }
        } catch (EOFException e) {
            server.removeUser(username, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setUsername(String username) {
        if (username == null) return;

        this.username = username;
        server.addUser(this.username, this.out);
    }

    void sendMessage(ChatMessageContainer message) throws IOException {
        out.writeUTF(message.toString());
    }
}
