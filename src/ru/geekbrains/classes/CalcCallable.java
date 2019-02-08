package ru.geekbrains.classes;

import java.util.concurrent.Callable;

class CalcCallable implements Callable<float[]> {
    private float[] array;

    @Override
    public float[] call() throws Exception {
        Threadless threadless = new Threadless(array);
        threadless.run();
        return threadless.array;
    }

    void setArray(float[] arr) {
        array = arr;
    }
}