package students;

import java.util.ArrayList;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}


//class SmartStudent implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getGpa() > 3;
//  }
//}
//
//class EnthusiasticStudent implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getCourses().size() > 3;
//  }
//}

public class School {
  
  public static void showAllStudents(Iterable<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
  }
  
//  public static List<Student> getSmartStudents(Iterable<Student> in, float threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getGpa() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
//  public static List<Student> getEnthusiasticStudents(Iterable<Student> in, int threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getCourses().size() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
  
  public static List<Student> getStudentsByCriterion(
      Iterable<Student> in, StudentCriterion criterion) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (criterion.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

  public static void main(String[] args) {
    List<Student> school = new ArrayList<>();
    school.add(Student.ofNameGpaCourses("Fred", 3.2F, "Math", "Art", "History"));
    school.add(Student.ofNameGpaCourses("Jim", 2.2F, "Art", "Geography"));
    school.add(Student.ofNameGpaCourses("Sheila", 3.8F, "Math", "Physics", "Chemistry", "Astrophysics"));
    showAllStudents(school);
    System.out.println("-------------------------------");
//    List<Student> smart = getSmartStudents(school, 3.0F);
    List<Student> smart = getStudentsByCriterion(
        school, new Student.SmartStudent());
    showAllStudents(smart);
    System.out.println("-------------------------------");
//    List<Student> enthusiastic = getEnthusiasticStudents(school, 3);
    List<Student> enthusiastic = getStudentsByCriterion(
        school, Student.getEnthusiasticCriterion());
    showAllStudents(enthusiastic);
  } 
}
