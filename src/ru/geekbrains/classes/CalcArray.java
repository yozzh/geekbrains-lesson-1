package ru.geekbrains.classes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

abstract class CalcArray {
    float[] array;
    private LocalDateTime startTime;

    CalcArray(int size) {
        array = new float[size];
        Arrays.fill(array, 1);
    }

    CalcArray(float[] arr) {
        array = arr;
    }

    private void startTimeCheck() {
        startTime = LocalDateTime.now();
    }

    private Duration getCalcDuration() {
        return Duration.between(startTime, LocalDateTime.now());
    }

    float calculateItem(int i) {
        return (float)(array[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
    }

    private String getName() {
        return getClass().getSimpleName();
    }

    private String getDurationMessage() {
        return String.format("%s: calc duration is %dms", getName(), getCalcDuration().toMillis());
    }

    abstract void calculate();

    void run() {
        System.out.println(String.format("%s: starting calculation..", getName()));
        startTimeCheck();
        calculate();

        System.out.println(getDurationMessage());
    }

    float sum() {
        float sum = 0;
        for(float item : array) {
            sum += item;
        }
        return sum;
    }
}
