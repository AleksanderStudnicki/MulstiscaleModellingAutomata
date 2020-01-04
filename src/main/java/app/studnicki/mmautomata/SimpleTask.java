package app.studnicki.mmautomata;

import java.util.stream.IntStream;

public class SimpleTask implements Runnable {

  private int[] origin;
  private int[] output;
  private int start;
  private int end;

  SimpleTask(int[] origin, int[] output, int start, int end) {
    this.origin = origin;
    this.output = output;
    this.start = start;
    this.end = end;
  }

  @Override
  public void run() {
    IntStream.range(start, end)
        .forEach(index -> output[index] = origin[index] / 2);
  }
}
