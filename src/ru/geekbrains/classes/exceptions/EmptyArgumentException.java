package ru.geekbrains.classes.exceptions;

public class EmptyArgumentException extends RuntimeException {
    public EmptyArgumentException() {
        super("This application requires the path to data file as first argument");
    }
}
