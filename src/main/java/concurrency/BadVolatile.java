package concurrency;

public class BadVolatile {

  private volatile int count = 0;

  class MyJob implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10_000; i++) {
        count++;
      }
    }

  }

  public void go() throws Throwable {
    Runnable r = new MyJob();
    Thread t1 = new Thread(r);
    t1.start();
    Thread t2 = new Thread(r);
    t2.start();
    t1.join();
    t2.join();
    System.out.println("Value of count is " + count);
  }

  public static void main(String[] args) throws Throwable {
    new BadVolatile().go();
  }
}
