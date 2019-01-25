package ru.geekbrains.classes.animals;

import ru.geekbrains.classes.Participant;

import static java.lang.System.out;

public abstract class Animal implements Participant {
    private String name;
    private boolean onDistance;
    private int maxRunDistance;
    private int maxJumpHeight;
    private int maxSwimDistance;

    public Animal(String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    public String getName() {
        return name;
    }


    public boolean isOnCourse() {
        return onDistance;
    }

    public abstract void voice();

    @Override
    public void run(int distance) {
        if (distance <= maxRunDistance) {
            out.println(name + " succeed ran through it");
        } else {
            out.println(name + " failed with run through it");
            this.onDistance = false;
        }
    }

    @Override
    public void jump(int distance) {
        if (distance <= maxJumpHeight) {
            out.println(name + " succeed jump over it");
        } else {
            out.println(name + " failed with jump over it");
            this.onDistance = false;
        }
    }

    @Override
    public void swim(int distance) {
        if (maxSwimDistance == 0) {
            out.println(name + " can't swim");
        }
        if (distance <= maxSwimDistance) {
            out.println(name + " succeed swam the distance");
        } else {
            out.println(name + " failed with smiw the distance");
            this.onDistance = false;
        }
    }

    @Override
    public String toString() {
        return name + (isOnCourse() ? " is on a course" : " is not on a course");
    }
}
