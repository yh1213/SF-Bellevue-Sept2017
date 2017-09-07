package store;

import java.awt.Color;

public class Shoe implements Sized, Colored {
  int size;
  Color color;

  public Shoe(int size, Color color) {
    this.size = size;
    this.color = color;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "Shoe{" + "size=" + size + ", color=" + color + '}';
  }


}
