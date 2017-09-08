package dynamic;

//@RunMe
public class ClassUnderTest {
  
//  @RunMe
  private String data;
  
  @RunMe
  public void testThis() {
    System.out.println("Running testThis");
  }

  @RunMe
  private void testThat() {
    System.out.println("Running testThat");
  }

//  @RunWithData(data="key1")
  @RunWithData(value="key1", num="246")
  public void tryThis(String data) {
    System.out.println("Running tryThis with " + data);
  }
  public void dontTestTheOther() {
    System.out.println("Running ... wait FAILED!!!");
  }
  
  @Override
  public String toString() {
    return "I'm the unit undertest";
  }
}
