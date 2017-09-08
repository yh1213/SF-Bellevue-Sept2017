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
    DoubleStream.generate(()->ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
        .forEach(s->System.out.println(s));
  }
}
