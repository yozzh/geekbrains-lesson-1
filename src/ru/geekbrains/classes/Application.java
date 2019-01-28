package ru.geekbrains.classes;

import ru.geekbrains.classes.exceptions.EmptyArgumentException;
import ru.geekbrains.classes.matrix.Loader;
import ru.geekbrains.classes.matrix.Sum;
import ru.geekbrains.classes.matrix.exceptions.MyArrayException;

import java.lang.reflect.Array;
import java.util.logging.Logger;

public class Application {
    private static Logger log = Logger.getLogger("Application");

    public static void main(String[] args) {
        if (Array.getLength(args) == 0) {
            throw new EmptyArgumentException();
        }
        String path = args[0];
        String[][] matrix = Loader.load(path);

        int sum;
        Sum sumCalculator = new Sum();
        try {
            sum = sumCalculator.calculate(matrix);
        } catch (MyArrayException ex) {
            log.warning(ex.getMessage());
            return;
        }
        log.info(String.format("The sum of all cells in matrix is %d", sum));
    }
}