package ru.geekbrains.classes;

public interface Participant {
    void run(int distance);
    void jump(int height);
    void swim(int distance);
    boolean isOnCourse();
    String getName();
}
