package ru.geekbrains.classes;

import java.io.IOException;

public interface ChatProviderReceiver {
    public void update(ChatMessageContainer messageContainer) throws IOException;
}
