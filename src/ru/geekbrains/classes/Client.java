package ru.geekbrains.classes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
    private final Logger log;
    private static final String DEFAULT_SERVER = "localhost";
    private static final int DEFAULT_PORT = 7777;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;
    private boolean readyForMessaging = false;

    public Client() {
        scanner = new Scanner(System.in);
        log = Logger.getLogger("Client");
    }

    void start() {
        System.out.println(String.format("Enter server address [%s]:", DEFAULT_SERVER));
        String serverAddress = receiveServerAddress();
        System.out.printf("Trying to connect to %s:%d\n", serverAddress, DEFAULT_PORT);

        try {
            socket = new Socket(serverAddress, DEFAULT_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                String message = in.readUTF();
                log.info(String.format("Server message received: %s", message));
                if (message.startsWith(MessageType.GET_NAME.toString())) {
                    System.out.println("Enter username:");
                    String name = scanner.nextLine();
                    out.writeUTF(name);
                } else if (message.startsWith(MessageType.READY_FOR_MESSAGING.toString())) {
                    readyForMessaging = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Scanner scanner = new Scanner(System.in);
                            while (scanner.hasNextLine()) {
                                if (!readyForMessaging) return;
                                String message = scanner.nextLine();
                                log.info(message);
                                try {
                                    out.writeUTF(message);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();
                } else if (message.startsWith(MessageType.MESSAGE.toString())) {
                    System.out.println(message.split("\t", -1)[1]);
                } else {
                    log.warning(String.format("Unknown message type: %s", message));
                }
            }
        } catch (EOFException e) {
            log.warning("Server has been turned off!");
            System.exit(-1);
        } catch (UnknownHostException e) {
            log.warning("Server is unreachable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String receiveServerAddress() {
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            address = DEFAULT_SERVER;
        }
        return address;
    }
}
