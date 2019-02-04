package ru.geekbrains.classes;

import java.text.SimpleDateFormat;
import java.util.Date;

class ChatMessage {
    String text;
    private Date date;

    ChatMessage(String text) {
        this.text = text;
        this.date = new Date();
    }

    String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
}
