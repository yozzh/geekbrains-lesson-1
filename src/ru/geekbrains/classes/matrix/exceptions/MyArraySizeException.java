package ru.geekbrains.classes.matrix.exceptions;

public class MyArraySizeException extends MyArrayException {
    public MyArraySizeException(int maxSize) {
        super(String.format("Matrix size greater than %d", maxSize));
    }
}
