package store;

public class Pair<E extends Sized & Colored> {
  E left;
  E right;

  public Pair(E left, E right) {
    this.left = left;
    this.right = right;
  }

  public E getLeft() {
    return left;
  }

  public void setLeft(E left) {
    this.left = left;
  }

  public E getRight() {
    return right;
  }

  public void setRight(E right) {
    this.right = right;
  }

  public boolean isMatched() {
    return left.getSize() == right.getSize()
        && left.getColor().equals(right.getColor());
  }
  
  @Override
  public String toString() {
    return "Pair{" + "left=" + left + ", right=" + right + '}';
  }
  
//  public static <F> void doStuff(F xxx) {
//  }
}
