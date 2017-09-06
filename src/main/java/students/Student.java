package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student {

  private final String name;
  private final float gpa;
  private final List<String> courses;

  public static Student ofNameGpaCourses(String name, float gpa, String... courses) {
//    List<String> c = new ArrayList<>();
//    c.addAll(courses);
    // NOT REALLY GOOD ENOUGH!!!
//    return new Student(name, gpa, Arrays.asList(courses));
    return new Student(name, gpa, Arrays.asList(courses.clone()));
  }

  private Student(String name, float gpa, List<String> courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public String getName() {
    return name;
  }

  public float getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return Collections.unmodifiableList(courses);
  }

  public Student setGpa(float newGpa) {
    return new Student(this.name, newGpa, this.courses);
  }

  @Override
  public String toString() {
    return "Student{" + "name=" + name + ", gpa=" + gpa + ", courses=" + courses + '}';
  }

  static class SmartStudent implements StudentCriterion {

    @Override
    public boolean test(Student s) {
      return s.getGpa() > 3;
    }
  }

  public static StudentCriterion getNameLengthCriterion() {
    return s->s.getName().length() > 4;
  }
  
  private static StudentCriterion enthusiasticStudent = 
      s -> s.getCourses().size() > 3 ;
  
//  private static StudentCriterion enthusiasticStudent = (Student s) -> {
//        return s.getCourses().size() > 3;
//      } ;

//  private static StudentCriterion enthusiasticStudent = /*new StudentCriterion() {*/
//
//  /*  @Override
//    public boolean test*/(Student s) -> {
//      return s.getCourses().size() > 3;
//    }
//  /*}*/;
//  
  // Anonymous inner class...
//  private static StudentCriterion enthusiasticStudent = new /*EnthusiasticStudent();
//  private static class EnthusiasticStudent implements */ StudentCriterion() {
//
//    @Override
//    public boolean test(Student s) {
//      return s.getCourses().size() > 3;
//    }
//  };
//  
  public static StudentCriterion getEnthusiasticCriterion() {
    return enthusiasticStudent;
  }
//  private static class EnthusiasticStudent implements StudentCriterion {
//
//    @Override
//    public boolean test(Student s) {
//      return s.getCourses().size() > 3;
//    }
//  }
}
