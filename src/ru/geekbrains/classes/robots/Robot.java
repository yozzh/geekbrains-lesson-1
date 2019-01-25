package ru.geekbrains.classes.robots;

import ru.geekbrains.classes.Participant;

public class Robot implements Participant {
    private String name;
    private boolean onDistance;
    private int maxRunDistance;
    private int maxJumpHeight;
    private int maxSwimDistance;

    public Robot(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    public String getName() {
        return name;
    }

    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(name + " succeed ran through it");
        } else {
            System.out.println(name + " failed with run through it");
            this.onDistance = false;
        }
    }

    @Override
    public void jump(int distance) {
        if (distance <= maxJumpHeight) {
            System.out.println(name + " succeed jump over it");
        } else {
            System.out.println(name + " failed with jump over it");
            this.onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if (maxSwimDistance == 0) {
            System.out.println(name + " can't swim");
        }
        if (distance <= maxSwimDistance) {
            System.out.println(name + " succeed swam the distance");
        } else {
            System.out.println(name + " failed with smiw the distance");
            this.onDistance = false;
        }
    }

    @Override
    public String toString() {
        return name + (isOnDistance() ? " is on distance" : " is not on distance");
    }
}
