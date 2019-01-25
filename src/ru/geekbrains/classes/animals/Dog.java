package ru.geekbrains.classes.animals;

public class Dog extends Animal {
    public Dog(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        super(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }

    @Override
    public void voice() {
        System.out.println("Bowow!");
    }

    public void swim() {
        System.out.println(this + " swims");
    }
}
