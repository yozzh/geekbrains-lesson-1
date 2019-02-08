package ru.geekbrains.classes;

class Threadless extends CalcArray {
    Threadless(int size) {
        super(size);
    }

    Threadless(float[] arr) {
        super(arr);
    }

    @Override
    void calculate() {
        for(int i = 0; i < array.length; i++) {
            array[i] = calculateItem(i);
        }
    }

    static CalcCallable getCallable(float[] array) {
        CalcCallable callable = new CalcCallable();
        callable.setArray(array);
        return callable;
    }
}
