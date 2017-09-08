package concurrency;

public class Visibility {
  public static void main(String[] args) throws Throwable {
    final boolean[] stop = {false};
    
    new Thread(()-> {
      while (!stop[0])
        ;
      System.out.println("Thread finishing!!!");
    }).start();
    
    Thread.sleep(1000);
    System.out.println("about to change stop value...");
    stop[0] = true;
    System.out.println("Main exiting...");
  }

}
