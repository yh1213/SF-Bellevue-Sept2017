package concurrency;

public class Visibility {
  private static volatile boolean stop = false;
  public static void main(String[] args) throws Throwable {
//    final volatile boolean[] stop = {false};
    
    new Thread(()-> {
//      while (!stop[0])
//        ;
      while (!stop)
        ;
      System.out.println("Thread finishing!!!");
    }).start();
    
    Thread.sleep(1000);
    System.out.println("about to change stop value...");
//    stop[0] = true;
    stop = true;
    System.out.println("Main exiting...");
  }

}
