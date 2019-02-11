package ru.geekbrains.classes;

import java.io.*;

public class Application {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) return;

        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] [%3$s]: %5$s %n");
        switch (args[0]) {
            case "server":
                Server server = new Server(7777);
                server.start();
                break;
            case "client":
                Client client = new Client();
                client.start();
                break;
        }
    }
}
