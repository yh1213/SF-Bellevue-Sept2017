package dynamic;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;

public class TestHarness {
  public static void main(String[] args) throws Throwable {
    Properties toTest = new Properties();
    toTest.load(new FileReader("Test.properties"));
    String className = toTest.getProperty("1");
    System.out.println("About to load class " + className);
    
    Class classToTest = Class.forName(className);
    Object obj = classToTest.newInstance();
    System.out.println("Created: " + obj);
    
    Method [] methods = classToTest.getDeclaredMethods();
    for (Method m : methods) {
      System.out.println("Found: " + m);
      RunMe annot = m.getAnnotation(RunMe.class);
      if (annot != null) {
        System.out.println("****** !!!! ");
        m.setAccessible(true);
        m.invoke(obj);
      }
      RunWithData annot2 = m.getAnnotation(RunWithData.class);
      if (annot2 != null) {
        System.out.println("DATA ****** !!!! ");
        m.setAccessible(true);
        String dat = toTest.getProperty(annot2.value());
        m.invoke(obj, dat);
      }
    }
  }
}
