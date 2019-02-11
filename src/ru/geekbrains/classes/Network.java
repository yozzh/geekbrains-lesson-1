package ru.geekbrains.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private final DataOutputStream out;
    private final DataInputStream in;

    public Network(String hostName, int port) throws IOException {
        Socket socket = new Socket(hostName, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(Thread.currentThread().isInterrupted()) {
                    try {
                        String msg = in.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
