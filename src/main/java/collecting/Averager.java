package collecting;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {

  private double sum;
  private long count;

  public void include(double num) {
    sum += num;
    count++;
  }

  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }

  public double get() {
    return sum / count;
  }
}

public class Averager {

  public static void main(String[] args) {
    long start = System.nanoTime();
    double avg
        = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
            .parallel()
            .limit(60_000_000L)
//            .map(x->Math.sin(x))
            .map(Math::sin)
//            .collect(() -> new Average(), (b, i) -> b.include(i), (bf, bi) -> bf.merge(bi))
            .collect(Average::new, Average::include, Average::merge)
            .get();
    long end = System.nanoTime();
    System.out.println("Average is " + avg);
    System.out.printf("Total execution time: %,12.9f\n", (end - start) / 1_000_000.0);
  }
}
