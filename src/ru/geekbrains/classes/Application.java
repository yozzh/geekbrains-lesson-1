package ru.geekbrains.classes;

import ru.geekbrains.classes.exceptions.EmptyArgumentException;
import ru.geekbrains.classes.matrix.Loader;
import ru.geekbrains.classes.matrix.Sum;
import ru.geekbrains.classes.matrix.exceptions.MyArrayException;

import java.util.logging.Logger;

public class Application {
    private static Logger log = Logger.getLogger("Application");

    public static void main(String[] args) {
        String path;
        try {
            path = args[0];
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new EmptyArgumentException();
        }
        String[][] matrix = Loader.load(path);

        int sum;
        Sum sumCalculator = new Sum();
        try {
            sum = sumCalculator.calculate(matrix);
        } catch (MyArrayException ex) {
            log.warning(ex.getMessage());
            return;
        }
        System.out.println(String.format("The sum of all cells in matrix is %d", sum));
    }
}