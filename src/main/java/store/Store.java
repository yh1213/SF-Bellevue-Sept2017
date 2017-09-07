package store;

import java.awt.Color;

public class Store {
  public static void main(String[] args) {
//    Pair<String> bad = new Pair<>("Fred", "Jones");
    
    Pair<Shoe> ps = new Pair<>(new Shoe(9, Color.BLACK), new Shoe(9, Color.BLUE));
    System.out.println("left is " + ps.getLeft());
//    ps.setRight(new Sock());
    System.out.println("ps is matched? " + ps.isMatched());
  }
}
