package ru.geekbrains.classes.matrix.exceptions;

public class MyArrayException extends RuntimeException {
    MyArrayException(String message) {
        super(message);
    }
    MyArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
