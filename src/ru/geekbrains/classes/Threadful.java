package ru.geekbrains.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Threadful extends CalcArray {
    public Threadful(int size) {
        super(size);
    }

    @Override
    void calculate() {
        int ap = Runtime.getRuntime().availableProcessors();

        // Create new executor with daemon threads via custom ThreadFactory
        ExecutorService es = Executors.newFixedThreadPool(ap, new CalcThreadFactory());

        try {
            int partLength = array.length / 2;
            float[][] parts = new float[2][partLength];
            System.arraycopy(array, 0, parts[0], 0, partLength);
            System.arraycopy(array, partLength, parts[1], 0, partLength);

            // Create threads for array parts
            List<CalcCallable> threads = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                CalcCallable callable = new CalcCallable();
                callable.setArray(parts[i]);
                threads.add(Threadless.getCallable(parts[i]));
            }

            // Run all created threads
            List<Future<float[]>> result = es.invokeAll(threads);

            System.arraycopy(result.get(0).get(), 0, array, 0, partLength);
            System.arraycopy(result.get(1).get(), 0, array, partLength, partLength);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
