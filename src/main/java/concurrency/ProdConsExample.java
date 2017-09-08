package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdConsExample {

  static void delay() {
    try {
      Thread.sleep(2);
    } catch (InterruptedException ie) {
      // nothing to do 
    }
  }

  static class Producer implements Runnable {

    BlockingQueue<int[]> q;

    public Producer(BlockingQueue<int[]> q) {
      this.q = q;
    }

    public void run() {
      for (int n = 0; n < 1_000; n++) {
        int[] dat = new int[2];
        dat[0] = n;
        delay();
        // prove the consumer verification is good!
        if (n != 2) {
          dat[1] = n;
        }
        delay();
        try {
          q.put(dat); dat = null;
        } catch (InterruptedException ex) {
          // nothing to do
        }
      }
      System.out.println("Producer finished...");
    }
  }

  static class Consumer implements Runnable {

    BlockingQueue<int[]> q;

    public Consumer(BlockingQueue<int[]> q) {
      this.q = q;
    }

    public void run() {
      for (int n = 0; n < 1_000; n++) {
        try {
          int[] dat = q.take();
          if (dat[0] != dat[1]
              || dat[0] != n) {
            System.out.println("ERROR!!! expected "
                + n + " Got " + dat[0] + " and " + dat[1]);
          }
        } catch (InterruptedException ex) {
          // Nothing to do
        }
      }
      System.out.println("Consumer finished...");
    }
  }

  public static void main(String[] args) {
    BlockingQueue<int[]> q = new ArrayBlockingQueue<>(10);
    Runnable prod = new Producer(q);
    Runnable cons = new Consumer(q);
    Thread p = new Thread(prod);
    Thread c = new Thread(cons);
    p.start();
    c.start();
    System.out.println("Main exiting...");
  }
}
