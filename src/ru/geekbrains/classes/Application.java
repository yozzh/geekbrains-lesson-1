package ru.geekbrains.classes;

public class Application {
    public static void main(String[] args) {
        int arraySize = 10000000;

        System.out.println("Threadful test");
        Threadful threadful = new Threadful(arraySize);
        threadful.run();
        System.out.println(String.format("Control sum check: %.2f", threadful.sum()));

        System.out.println("Threadless test");
        Threadless threadless = new Threadless(arraySize);
        threadless.run();
        System.out.println(String.format("Control sum check: %.2f", threadful.sum()));
    }
}
