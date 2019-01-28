package ru.geekbrains.classes.matrix.exceptions;

public class MyArrayDataException extends MyArrayException {
    public MyArrayDataException(int ix, int iy, String val, NumberFormatException ex) {
        super(
                String.format("Cell [%d, %d] contains no number: \"%s\"", ix + 1, iy + 1, val),
                ex
        );
    }
}
