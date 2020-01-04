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

  static ExecutorService exec = Executors.newFixedThreadPool(10);

  public static void run() {

    List<Future<?>> futures = new ArrayList<>();

    int size = 10000;

    int[] arr = IntStream.range(0, size).toArray();
    int[] out = new int[size];

    IntStream.range(0, 10)
        .forEach(index -> {
          int start = size / 10 * index;
          int end = size / 10 * index + size / 10;
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

    System.out.println(Arrays.toString(arr));
    System.out.println();
    System.out.println(Arrays.toString(out));
  }
}
