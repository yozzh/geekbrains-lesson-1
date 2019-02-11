package ru.geekbrains.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class Handler extends Thread {
    private final Logger log;
    private Socket socket;
    private Server server;
    private String username;
    private DataInputStream in;
    private DataOutputStream out;

    Handler(Server server, Socket socket) throws IOException {
        log = Logger.getLogger("Handler");
        this.server = server;
        this.socket = socket;
    }

    public void run() {
        log.info("New connection has been started!");
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                out.writeUTF(MessageType.GET_NAME.toString());
                this.setUsername(in.readUTF());
                break;
            }

            out.writeUTF(MessageType.READY_FOR_MESSAGING.toString());

            while (true) {
                String message = in.readUTF();
                if (message.equals("")) {
                    return;
                }
                log.info("Message received!");

                server.sendUserMessage(username, message);
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

}
