package ru.geekbrains.classes;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client implements ChatProviderReceiver {
    private final Logger log;
    private static final String DEFAULT_SERVER = "localhost";
    private static final int DEFAULT_PORT = 7777;

    private ChatProvider chatProvider;
    private Scanner scanner;

    Client() {
        scanner = new Scanner(System.in);
        log = Logger.getLogger("Client");
    }

    void start() {
        System.out.println(String.format("Enter server address [%s]:", DEFAULT_SERVER));
        String serverAddress = requestServerAddress();
        System.out.printf("Trying to connect to %s:%d\n", serverAddress, DEFAULT_PORT);

        try {
            Socket socket = new Socket(serverAddress, DEFAULT_PORT);
            chatProvider = new ChatProvider(socket);
            chatProvider.setReceiver(this);
            Thread providerThread = new Thread(chatProvider);
            providerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String requestServerAddress() {
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            address = DEFAULT_SERVER;
        }
        return address;
    }

    @Override
    public void update(ChatMessageContainer messageContainer) throws IOException {
        switch (messageContainer.getType()) {
            case GET_NAME:
                System.out.println("Enter username:");
                String name = scanner.nextLine();
                chatProvider.sendMessage(new ChatMessageContainer(
                    MessageType.REGISTER,
                    name
                ));
                break;
            case READY_FOR_MESSAGING:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (scanner.hasNextLine()) {
                            String messageText = scanner.nextLine();
                            log.info(messageText);
                            try {
                                chatProvider.sendMessage(new ChatMessageContainer(
                                        MessageType.MESSAGE,
                                        messageText
                                ));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            case MESSAGE:
                System.out.println(String.format("[%s] %s", messageContainer.getDate(), messageContainer.getContent()));
                break;
            default:
                log.warning(String.format("Unknown message type: %s", messageContainer.getType().toString()));
                break;
        }
    }
}
