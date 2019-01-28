package ru.geekbrains.classes.matrix;

import ru.geekbrains.classes.matrix.exceptions.MyArrayDataException;
import ru.geekbrains.classes.matrix.exceptions.MyArraySizeException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sum {
    private int maxDimension = 4;
    public Sum() {}
    public Sum(int maxDimension) {
        this.maxDimension = maxDimension;
    }

    private void checkDimension(int dimension) {
        if (dimension > maxDimension) {
            throw new MyArraySizeException(maxDimension);
        }
    }

    private int[][] parseToInt(String[][] array) {
        this.checkDimension(Array.getLength(array));
        int[][] result = new int[Array.getLength(array)][];

        int iy = 0;
        for (String[] line : array) {
            this.checkDimension(Array.getLength(line));
            result[iy] = new int[Array.getLength(line)];

            int ix = 0;
            for (String cell : line) {
                try {
                    result[iy][ix] = Integer.parseInt(cell, 10);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(iy, ix, cell);
                }
                ix++;
            }

            iy++;
        }

        return result;
    }

    public int calculate(String[][] array) {
        int[][] arrayOfInt = parseToInt(array);
        return Arrays.stream(arrayOfInt)
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}
