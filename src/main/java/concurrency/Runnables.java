package concurrency;

class MyRunnable implements Runnable {
  private int i = 0;
  
  @Override
  public void run() {
    for (; i < 10_000; i++) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
    }
  }
}

public class Runnables {
  public static void main(String[] args) {
    Runnable r = new MyRunnable();
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
    System.out.println(Thread.currentThread().getName() + " [main exiting...]");
  }
}
