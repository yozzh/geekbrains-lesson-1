package ru.geekbrains.classes;

import java.util.concurrent.ThreadFactory;

class CalcThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
