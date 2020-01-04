package app.studnicki.mmautomata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ThreadTester {

  static int threads = 40;
  static ExecutorService exec = Executors.newFixedThreadPool(threads);
  static int size = 150000;


  public static int[] multipleThreadsArrayOperations() {

    List<Future<?>> futures = new ArrayList<>();

    int[] arr = IntStream.range(0, size).toArray();
    int[] out = new int[size];

    long startTime = System.nanoTime();

    IntStream.range(0, threads)
        .forEach(index -> {
          int start = size / threads * index;
          int end = size / threads * index + size / threads;
          Future<?> f = exec.submit(new SimpleTask(arr, out, start, end));
          futures.add(f);
        });

    for (Future<?> f : futures) {
      try {
        f.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000;

    System.out.println("Duration: " + duration + " millis");

    exec.shutdown();

    return out;
  }

  public static int[] singleThreadArrayOperations() {
    int[] arr = IntStream.range(0, size).toArray();
    int[] out = new int[size];

    long startTime = System.nanoTime();

    IntStream.range(0, size)
        .forEach(index -> IntStream.range(index, size)
            .forEach(i -> out[index] += arr[i]));

    long endTime = System.nanoTime();
    long duration = (endTime - startTime) / 1000000;

    System.out.println("Duration: " + duration + " millis");

    return out;
  }
}
