package ru.geekbrains.classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatMessageContainer {
    private MessageType type;
    private String content;

    private Date date;

    ChatMessageContainer(MessageType type) {
        this.type = type;
        this.date = new Date();
    }

    ChatMessageContainer(MessageType type, String content) {
        this.type = type;
        this.content = content;
        this.date = new Date();
    }

    ChatMessageContainer(MessageType type, String content, Date date) {
        this.type = type;
        this.content = content;
        this.date = date;
    }

    MessageType getType() {
        return type;
    }

    String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    static ChatMessageContainer createFromString(String message) {
        String[] parts = message.split("\t", -2);
        Date date = new Date(Long.parseLong(parts[1]));
        MessageType type = MessageType.valueOf(parts[0]);
        String content = parts.length > 2 ? parts[2] : null;
        return new ChatMessageContainer(type, content, date);
    }

    @Override
    public String toString() {
        List<String> messageQuery = new ArrayList<>();
        messageQuery.add(type.toString());
        messageQuery.add(Long.toString(date.getTime()));
        if (content != null) {
            messageQuery.add(content);
        }
        return String.join("\t", messageQuery);
    }

    boolean isEmpty() {
        return (content == null);
    }
}
