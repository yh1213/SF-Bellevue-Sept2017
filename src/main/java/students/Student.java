package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student {
  private final String name;
  private final float gpa;
  private final List<String> courses;

  public static Student ofNameGpaCourses(String name, float gpa, String ... courses) {
//    List<String> c = new ArrayList<>();
//    c.addAll(courses);
                                  // NOT REALLY GOOD ENOUGH!!!
    return new Student(name, gpa, Arrays.asList(courses));
  }
  
  private Student(String name, float gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }
  
  public List<String> getCourses() {
    return Collections.unmodifiableList(courses);
  }
  
  public Student setGpa(float newGpa) {
    return new Student(this.name, newGpa, this.courses);
  }
}
